package com.mutualmobile.praxis.domain.repositories

import java.io.File

interface RandomFileService {
  fun getTempFile(): File
}