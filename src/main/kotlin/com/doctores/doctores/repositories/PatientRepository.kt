package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Patient
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation

@Repository
interface PatientRepository: JpaRepository<Patient, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from pacientes where id_paciente = :id", nativeQuery = true)
    fun getPatientById(id:Long): List<Patient>

    @Query("delete from pacientes where id_paciente =:id", nativeQuery = true)
    fun deletePatientById(id:Long):String

    @Modifying
    @Query("update pacientes SET nombre = :nombre, apellido = :apellido, identificacion = :identificacion, telefono = :telefono WHERE id_paciente = :id", nativeQuery = true)
    fun updatePatientById(
        @Param("id") id: Long,
        @Param("nombre") nombre: String,
        @Param("apellido") apellido: String,
        @Param("identificacion") identificacion: String,
        @Param("telefono") telefono: Long
    )
}