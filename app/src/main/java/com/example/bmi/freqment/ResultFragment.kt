package com.example.bmi.freqment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bmi.R
import com.example.bmi.databinding.FragmentHeightWeightBinding
import com.example.bmi.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentResultBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setResult()
        binding.Done.setOnClickListener {
            toInfoFreqment()
        }

    }

    private fun setResult() {
       val BMI=arguments?.getString("BMI")
        if(BMI!! < 18.5.toString()){
            binding.layoutResult.setBackgroundResource(R.drawable.blue)
            binding.DescriptionResult.text="Underweight"
        }
        else if(BMI!! >= 18.5.toString() && BMI!! <= 24.9.toString()){
            binding.layoutResult.setBackgroundResource(R.drawable.green)
            binding.DescriptionResult.text="Normal"
        }
        else if(BMI!! >= 25.toString() && BMI!! <= 29.9.toString()){
            binding.layoutResult.setBackgroundResource(R.drawable.yellwo)
            binding.DescriptionResult.text="Overweight"
        }
        else if(BMI!! >= 30.toString() && BMI!! <= 34.9.toString()){
            binding.layoutResult.setBackgroundResource(R.drawable.orange)
            binding.DescriptionResult.text="Obesity"
        }
        else if(BMI!! >= 35.toString() ){
            binding.layoutResult.setBackgroundResource(R.drawable.red)
            binding.DescriptionResult.text="Extremely obese"
        }
        binding.Result.text=BMI
    }


    private fun toInfoFreqment() {
        findNavController().navigate(R.id.action_Result_to_HeighWeight)
    }

}