package com.example.get_set_go

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.databinding.FragmentForgotPasswordBinding
import com.example.get_set_go.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class forgotPasswordFragment : Fragment() {

    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater)
        binding.signupBtnL.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_signupFragment2)
        }
        binding.confirmF.setOnClickListener {

            val email:String = binding.emailInputF.text.toString().trim{it <=' '}
            if(email.isEmpty()){
                Toast.makeText(this@forgotPasswordFragment.requireContext(),"Please enter E-mail address",Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this@forgotPasswordFragment.requireContext(),"E-mail sent successfully to reset your password",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@forgotPasswordFragment.requireContext(),it.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }



        }



        return binding.root
    }
}