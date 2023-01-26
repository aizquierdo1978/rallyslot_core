package com.aizquierdo.rallyslot.core.grpc.service;

import com.aizquierdo.rallyslot.core.exception.EntityNotFoundException;
import com.aizquierdo.rallyslot.core.piloto.PilotoData;
import com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceGrpc;
import com.aizquierdo.rallyslot.core.piloto.PilotoQuery;
import com.aizquierdo.rallyslot.core.service.piloto.PilotoService;
import com.aizquierdo.rallyslot.core.dto.piloto.Piloto;
import com.aizquierdo.rallyslot.core.util.DateUtils;
import com.aizquierdo.rallyslot.core.util.RallyslotConstants;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@GrpcService
public class PilotoServiceGrpc extends PilotoGrpcServiceGrpc.PilotoGrpcServiceImplBase {

    @Autowired
    private PilotoService pilotoService;

    @Override
    public void getPiloto(PilotoQuery request, StreamObserver<PilotoData> responseObserver) {
        try {
            Piloto piloto = pilotoService.getPiloto(request.getPilotoId());
            PilotoData pilotoData = parsePilotoData(piloto);
            responseObserver.onNext(pilotoData);
        } catch (EntityNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
            return;
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
            return;
        }
        responseObserver. onCompleted();
    }

    @Override
    public void getPilotos(Empty request, StreamObserver<PilotoData> responseObserver) {
        List<Piloto> pilotos = pilotoService.fetchPilotoList();
        for (Piloto piloto: pilotos) {
            PilotoData pilotoData = parsePilotoData(piloto);
            responseObserver.onNext(pilotoData);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void createPiloto(PilotoData request, StreamObserver<PilotoData> responseObserver) {
        try {
            Piloto piloto = parsePiloto(request);
            Piloto pilotoCreado = pilotoService.savePiloto(piloto);
            responseObserver.onNext(parsePilotoData(pilotoCreado));
        } catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
            return;
        }

        responseObserver.onCompleted();
    }

    @Override
    public void updatePiloto(PilotoData request, StreamObserver<PilotoData> responseObserver) {
        try {
            Piloto piloto = pilotoService.getPiloto(request.getPilotoId());
            piloto.setPilotoName(request.getPilotoName());
            piloto.setModificationUser(request.getModificationUser());
            piloto.setModificationDate(new Date(request.getModificationDate()));
            pilotoService.updatePiloto(piloto, request.getPilotoId());
            PilotoData pilotoData = parsePilotoData(pilotoService.getPiloto(piloto.getPilotoId()));
            responseObserver.onNext(pilotoData);
        }catch (EntityNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
            return;
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
            return;
        }

        responseObserver.onCompleted();
    }

    @Override
    public void deletePiloto(PilotoQuery request, StreamObserver<Empty> responseObserver) {
        try {
            pilotoService.deletePilotoById(request.getPilotoId(), "core", new Date());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (EntityNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
            return;
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
            return;
        }
    }

    private PilotoData getPilotoData(Long pilotoId) {
        Piloto piloto = pilotoService.getPiloto(pilotoId);
        return parsePilotoData(piloto);
    }

    private PilotoData parsePilotoData(Piloto piloto) {
        PilotoData.Builder pilotoDataBuilder = PilotoData.newBuilder()
                .setPilotoId(piloto.getPilotoId())
                .setPilotoName(piloto.getPilotoName());

        if(piloto.getCreationUser()!=null) {
            pilotoDataBuilder.setCreationUser(piloto.getCreationUser());
        }

        if(piloto.getCreationDate()!=null) {
            pilotoDataBuilder.setCreationDate(piloto.getCreationDate().getTime());
        }

        if(piloto.getModificationUser()!=null) {
            pilotoDataBuilder.setModificationUser(piloto.getModificationUser());
        }

        if(piloto.getModificationDate()!=null) {
            pilotoDataBuilder.setModificationDate(piloto.getModificationDate().getTime());
        }

        if(piloto.getDeleteUser()!=null) {
            pilotoDataBuilder.setDeleteUser(piloto.getDeleteUser());
        }

        if(piloto.getDeleteDate()!=null) {
            pilotoDataBuilder.setDeleteDate((piloto.getDeleteDate().getTime()));
        }

        return pilotoDataBuilder.build();
    }

    private Piloto parsePiloto(PilotoData pilotoData) {
        Piloto piloto = new Piloto();
        piloto.setPilotoId(pilotoData.getPilotoId());
        piloto.setPilotoName(pilotoData.getPilotoName());
        piloto.setCreationUser(pilotoData.getCreationUser());
        if(pilotoData.getCreationDate() > RallyslotConstants.ZERO) {
            piloto.setCreationDate(new Date(pilotoData.getCreationDate()));
        }
        piloto.setModificationUser(pilotoData.getModificationUser());
        if(pilotoData.getModificationDate() > RallyslotConstants.ZERO) {
            piloto.setModificationDate(new Date(pilotoData.getModificationDate()));
        }
        piloto.setDeleteUser(pilotoData.getDeleteUser());
        if(pilotoData.getDeleteDate() > RallyslotConstants.ZERO) {
            piloto.setDeleteDate(new Date(pilotoData.getDeleteDate()));
        }
        return piloto;
    }
}
