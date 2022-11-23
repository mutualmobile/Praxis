package com.mutualmobile.praxis.sample

import android.os.Bundle
import com.mutualmobile.praxis.root.BaseSplitActivity

class SampleActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }
}