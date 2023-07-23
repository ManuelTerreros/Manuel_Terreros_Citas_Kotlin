package com.doctores.doctores.services


import com.doctores.doctores.domains.entity.Patient
import com.doctores.doctores.domains.request.PatientRequest
import com.doctores.doctores.domains.request.UpdatePatientRequest
import com.doctores.doctores.domains.responses.DoctorResponse
import com.doctores.doctores.domains.responses.PatientResponse
import com.doctores.doctores.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.math.E

@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository

    fun createPatient(request: PatientRequest): PatientResponse{
        try {
            val pat = patientRepository.save(
                    Patient(
                        nombre = request.nombre,
                        apellido = request.apellido,
                        identificacion = request.identificacion,
                        telefono = request.telefono,
                    )
                    )
            return PatientResponse(
                message = "Patient created successfully",
                patient = pat
            )
        }catch (e:Error){
            throw Error(e.message)
        }

    }
    fun updatePatient(id: Long, request: UpdatePatientRequest): PatientResponse {
        try {
            val pat = getPatientById(id)
            val new = Patient(
                //idPat = pat.idpaciente,
                nombre = if (request.nombre!==null) request.nombre else pat.nombre,
                apellido = if (request.apellido!==null) request.apellido else pat.apellido,
                identificacion = if(request.identificacion!==null) request.identificacion else pat.identificacion,
                telefono = if (request.telefono!==null) request.telefono else pat.telefono

            )
            val updatePatient = patientRepository.save(new)
            return PatientResponse("Patient update", updatePatient)
        } catch (ex: Error) {
            throw Error(ex.message)
        }
    }

    fun getAllPatients(): List<Patient>{
        var response = patientRepository.findAll()
        return response
    }

    fun getPatientById(id:Long): Patient {
        var patient = patientRepository.findByIdOrNull(id)
        if(patient != null){
            return patient
        }
        throw Error("Patient not found")

    }

    fun deletePatientById(id:Long):PatientResponse{
        try {
            var pat = getPatientById(id)
            patientRepository.deletePatientById(id)
            return PatientResponse("Delete Successfully", pat)
        }catch (e: Error){
            return PatientResponse(e.message)
        }

    }

}