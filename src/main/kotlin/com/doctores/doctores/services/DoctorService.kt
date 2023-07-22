package com.doctores.doctores.services

import com.doctores.doctores.domains.request.CreateDoctorRequest
import com.doctores.doctores.domains.responses.CreateDoctorResponse
import com.doctores.doctores.repositories.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import com.doctores.doctores.domains.entity.Doctor
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException

@Service
class DoctorService {
    @Autowired
    private lateinit var doctorRepository: DoctorRepository

    // Dejemoslo Quieto
    fun createDoctor(request: CreateDoctorRequest): CreateDoctorResponse {
        val doctor = doctorRepository.save(
                Doctor(
                        nombre = request.nombre,
                        apellido = request.apellido,
                        especialidad = request.especialidad,
                        correo = request.correo,
                        consultorio = request.consultorio,
                )
        )
        return CreateDoctorResponse(
                idDoctor = 1,
                nombre = request.nombre,
                apellido = request.apellido,
                especialidad = request.especialidad,
                correo = request.correo,
                consultorio = request.consultorio,
                createAt = Instant.now()
        )
    }

    // Dejemoslo quieto
    fun getAllDoctors(): List<Doctor> {
        var response = doctorRepository.findAll()
        return response
    }

    // Dejemoslo Quieto
    fun getDoctorById(id: Long): List<Doctor> {
        var doctor = doctorRepository.getByDoctorId(id)

        return doctor

    }

    fun updateDoctor(id: Long, request: CreateDoctorRequest ): String {
        try {
            doctorRepository.updateDoctorById(id, request.especialidad)
            return "Doctor Updated"
        } catch (ex: EmptyResultDataAccessException) {
            // If Doctor doesn't exist in BD
            return "Error: Doctor not found"
        } catch (ex: DataAccessException) {
            return "Doctor Updated"
        }
    }

    fun deleteDoctor(id: Long): String{
        try {
           doctorRepository.deleteDoctorByIdDoctor(id)
            return "Doctor removed"
        }catch (ex: DataAccessException){
            return "Doctor removed"
            }

    }


}