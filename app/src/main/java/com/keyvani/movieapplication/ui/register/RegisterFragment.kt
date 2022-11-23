package com.keyvani.movieapplication.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import com.keyvani.movieapplication.R
import com.keyvani.movieapplication.databinding.FragmentRegisterBinding
import com.keyvani.movieapplication.response.register.BodyRegister
import com.keyvani.movieapplication.utils.StoreUserData
import com.keyvani.movieapplication.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var userData: StoreUserData

    @Inject
    lateinit var body: BodyRegister

    //Other
    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            btnSubmit.setOnClickListener {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                //Validation
                if (name.isNotEmpty() || password.isNotEmpty() || email.isNotEmpty()) {
                    body.name = name
                    body.email = email
                    body.password = password

                } else {
                    Snackbar.make(it, getString(R.string.fillAllFields), Snackbar.LENGTH_SHORT).show()
                }
                //Send data
                viewModel.sendRegisterUser(body)

                //Loading
                viewModel.loading.observe(viewLifecycleOwner){ isShown->
                    if(isShown){
                        pbSubmitLoading.visibility=View.VISIBLE
                        btnSubmit.visibility=View.INVISIBLE
                    }else{
                        pbSubmitLoading.visibility=View.GONE
                        btnSubmit.visibility=View.VISIBLE

                    }
                }

                //Register
                viewModel.registerUser.observe(viewLifecycleOwner){ response->
                    lifecycle.coroutineScope.launchWhenCreated {
                        userData.saveUserToken(response.name.toString())
                    }
                }

            }

        }
    }
}