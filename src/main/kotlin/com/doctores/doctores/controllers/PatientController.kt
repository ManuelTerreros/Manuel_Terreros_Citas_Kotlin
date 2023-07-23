package com.doctores.doctores.controllers
import com.doctores.doctores.constants.*
import com.doctores.doctores.domains.entity.Patient
import com.doctores.doctores.domains.request.PatientRequest
import com.doctores.doctores.domains.request.UpdatePatientRequest
import com.doctores.doctores.domains.responses.DoctorResponse
import com.doctores.doctores.domains.responses.HealthCheckResponse
import com.doctores.doctores.domains.responses.PatientResponse
import com.doctores.doctores.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class PatientController {
    @Autowired
    private lateinit var patientService: PatientService

    @GetMapping(Patient)
    fun getAllPatients(): List<Patient> = patientService.getAllPatients()

    @PostMapping(CreatePatients)
    fun createPatient(@RequestBody @Validated request: PatientRequest): ResponseEntity<PatientResponse> {
        try {
            return ResponseEntity(patientService.createPatient(request), HttpStatus.CREATED)
        }catch (e : Error){
            return ResponseEntity(PatientResponse(message = e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping(GetPatientById)
    fun getPatienttById(
            @PathVariable("id") id: Long
    ): PatientResponse {
        try {
         return PatientResponse("Patient $id found", patientService.getPatientById(id))
        } catch (e: Error){
            return PatientResponse(e.message)
        }
    }

    @PutMapping(UpdatePatient)
    fun updatePatient(
        @PathVariable("id") id: Long,
        @RequestBody @Validated request: UpdatePatientRequest
    ): ResponseEntity<PatientResponse> {
        try {
            return ResponseEntity(patientService.updatePatient(id, request), HttpStatus.ACCEPTED)
        }catch (e : Error) {
            return ResponseEntity(PatientResponse(message = e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(DeletePatient)
    fun deletePatient(
            @PathVariable("id")id:Long
    ): ResponseEntity<PatientResponse> {
        try {
            return ResponseEntity(patientService.deletePatientById(id), HttpStatus.ACCEPTED)
        } catch (e: Error) {
            return ResponseEntity(PatientResponse(message = e.message), HttpStatus.BAD_REQUEST)
        }
    }


}