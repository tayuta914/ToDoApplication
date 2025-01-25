package com.example.todoapplication.page.deteail

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.application.R
import com.example.application.databinding.TodoDetailFragmentBinding
import com.example.todoapplication.model.todo.ToDo
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoDetailFragment : Fragment(R.layout.todo_detail_fragment) {
    private val vm: ToDoDetailViewModel by viewModels()

    private var _binding: TodoDetailFragmentBinding? = null
    private val binding: TodoDetailFragmentBinding get() = _binding!!

    private val args: ToDoDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setFragmentResultListener("edit") { _, data ->
            val todo: ToDo = data.getParcelable("todo")!!
            vm.todo.value = todo
        }
        setFragmentResultListener("confirm") { _, data ->
            val which = data.getInt("result")
            if (which == DialogInterface.BUTTON_POSITIVE) {
                vm.delete()
            }
        }
        if (savedInstanceState == null) {
            vm.todo.value = args.todo
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this._binding = TodoDetailFragmentBinding.bind(view)

        vm.todo.observe(viewLifecycleOwner) { todo ->
            binding.titleText.text = todo.title
            binding.detailText.text = todo.detail
        }
        vm.errorMessage.observe(viewLifecycleOwner) { msg ->
            Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
            vm.errorMessage.value = ""
        }
        vm.deleted.observe(viewLifecycleOwner) { deleted ->
            if (deleted) {
                // 第２引数なしだとダイアログを閉じるだけになる
                findNavController().popBackStack(
                    R.id.mainFragment,
                    false
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val action =
                    ToDoDetailFragmentDirections.actionToDoDetailFragmentToEditToDoFragment(
                        // 画面遷移時にもViewModelの状態を渡す
                        vm.todo.value!!
                    )
                findNavController().navigate(action)
                true
            }

            R.id.action_delete -> {
                findNavController().navigate(R.id.action_toDoDetailFragment_to_confirmDialogFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}