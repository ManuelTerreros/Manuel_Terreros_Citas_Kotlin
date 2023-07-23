package com.doctores.doctores.domains.responses


import com.doctores.doctores.domains.entity.Patient
import java.time.Instant

data class PatientResponse (

        val message: String?,
        val patient: Patient? = null,

)
