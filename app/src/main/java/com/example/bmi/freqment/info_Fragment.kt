package com.example.bmi.freqment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bmi.R
import com.example.bmi.databinding.FragmentHeightWeightBinding
class info_Fragment : Fragment() {
     private lateinit var binding:FragmentHeightWeightBinding
     private var Gender:String=""
    private var currentWeight: Int = 30
    private val minWeight = 30
    private val maxWeight = 200

    private var currentAge: Int = 10
    private val minAge = 10
    private val maxAge = 100

    private var currentHeight: Int = 70

    companion object {
        const val GENDER_MALE = "Male"
        const val GENDER_FEMALE = "Female"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHeightWeightBinding.inflate(inflater, container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Female.setOnClickListener {
            selectGender(GENDER_FEMALE)
        }
        binding.Male.setOnClickListener {
            selectGender(GENDER_MALE)
        }

        binding.increaseWeight.setOnClickListener {
            updateWeight(1)
        }

       binding.decreaseWeight.setOnClickListener {
           updateWeight(-1)
       }
       binding.increaseAge.setOnClickListener {
           updateAge(1)
       }
        binding.decreaseAge.setOnClickListener {
            updateAge(-1)
        }
        binding.bmiCalc.setOnClickListener {
            BMI(currentHeight.toFloat(),currentWeight.toFloat())
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val newHeight = progress + 1
                if (newHeight in 70..200) {
                    currentHeight = newHeight
                    binding.height.text = currentHeight.toString()
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


    }

  private fun BMI(height: Float, weight: Float){
        val bundle=Bundle()
        var heightInMeter : Float = height/100
        val BMI = String.format("%.2f", weight / (heightInMeter * heightInMeter))
        bundle.putString("BMI",BMI)
        findNavController().navigate(R.id.action_HeighWeight_to_Result, bundle)
    }

    private fun updateAge(increment: Int) {
        val newAge = currentAge + increment
        if (newAge in minAge..maxAge) {
            currentAge = newAge
            binding.age.text = currentAge.toString()
        }
        else{
            binding.popWindow.visibility=View.VISIBLE
            binding.TryAgain.setOnClickListener {
                binding.popWindow.visibility=View.GONE
            }
        }
    }
    private fun updateWeight(increment: Int) {
        val newWeight = currentWeight + increment
        if (newWeight in minWeight..maxWeight) {
            currentWeight = newWeight
            binding.weight.text = currentWeight.toString()
        }
        else{
            binding.popWindow.visibility=View.VISIBLE
            binding.TryAgain.setOnClickListener {
                binding.popWindow.visibility=View.GONE
            }
        }
    }

    private fun selectGender(gender: String) {
        when (gender) {
            GENDER_MALE -> {
                binding.Female.setBackgroundResource(R.drawable.btn)
                binding.Male.setBackgroundResource(R.drawable.btn_click)
                Gender = GENDER_MALE
            }
            GENDER_FEMALE -> {
                binding.Male.setBackgroundResource(R.drawable.btn)
                binding.Female.setBackgroundResource(R.drawable.btn_click)
                Gender = GENDER_FEMALE
            }
        }
    }

}







