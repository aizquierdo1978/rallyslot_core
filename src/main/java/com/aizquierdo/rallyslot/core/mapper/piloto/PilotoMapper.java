package com.aizquierdo.rallyslot.core.mapper.piloto;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.aizquierdo.rallyslot.core.entity.piloto.PilotoEntity;

/**
 * La interfaz que mapea los pilotoes.
 */
public interface PilotoMapper {

	/**
	 * Obtiene el piloto.
	 *
	 * @param pilotoId the piloto id
	 * @return el piloto
	 */
	@Select("SELECT pilotoId, pilotoName,creationDate,creationUser,modificationDate,modificationUser FROM PILOTO WHERE pilotoId = #{pilotoId} AND deleteDate IS NULL")
	PilotoEntity getPiloto(@Param("pilotoId") Long pilotoId);

	/**
	 * Obtiene el listado de pilotos.
	 *
	 * @return el pilotos
	 */
	@Select("SELECT pilotoId, pilotoName,creationDate,creationUser,modificationDate,modificationUser FROM PILOTO WHERE deleteDate IS NULL")
	List<PilotoEntity> getPilotos();

	/**
	 * Inserta el piloto.
	 *
	 * @param piloto el piloto
	 * @return the int
	 */
	@Insert("INSERT INTO PILOTO(pilotoName,creationDate,creationUser) VALUES (#{pilotoName}, #{creationDate}, #{creationUser})")
	@Options(useGeneratedKeys = true, keyProperty = "pilotoId", keyColumn = "pilotoId")
	public long insertPiloto(PilotoEntity piloto);

	/**
	 * Actualiza el piloto.
	 *
	 * @param piloto the piloto
	 * @return the int
	 */
	@Update("Update PILOTO set pilotoName=#{pilotoName}, modificationDate=#{modificationDate}, modificationUser=#{modificationUser} where pilotoId = #{pilotoId}")
	public int updatePiloto(PilotoEntity piloto);

	/**
	 * Borra el piloto.
	 *
	 * @param piloto the piloto
	 * @return the int
	 */
	@Update("Update PILOTO set deleteUser=#{deleteUser}, deleteDate=#{deleteDate} where pilotoId = #{pilotoId}")
	public int deletePiloto(PilotoEntity pilotoEntity);

}
