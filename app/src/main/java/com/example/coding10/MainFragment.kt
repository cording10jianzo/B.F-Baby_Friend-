package com.example.coding10

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val REQUEST_PHONE_CALL = 1
    private lateinit var displayList: List<CommonItems>
    lateinit var binding: FragmentMainBinding
    private lateinit var itemTouchHelper: ItemTouchHelper
    private val listAdapter by lazy {
        MainListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecyclerview.adapter = listAdapter

        //스와이프 이벤트 부착
        itemTouchHelper = ItemTouchHelper(MainListItemHelper(requireActivity()))
        itemTouchHelper.attachToRecyclerView(binding.mainRecyclerview)

        displayList = displayData()
        listAdapter.addItems(displayList)
        listAdapter.itemClick = object : MainListAdapter.ItemClick {
            override fun onClick(position: Int) {

                val i = Intent(activity, DetailActivity::class.java).apply {
                    putExtra("DATA", displayList[position] as MainItems)
                }
                startActivity(i)
            }

            override fun onLongClick(position: Int) {
                var builder = AlertDialog.Builder(context!!)

                builder.setTitle("연락처 삭제")
                builder.setMessage("정말로 삭제하시겠습니까?")
                builder.setIcon(R.drawable.finish)

                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, p1: Int) {
                        when (p1) {
                            DialogInterface.BUTTON_POSITIVE -> {
//                                (displayList as MutableList).removeAt(position)
//                                (binding.mainRecyclerview.adapter as MainListAdapter).notifyDataSetChanged()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {

                            }
                        }
                    }
                }
                builder.setPositiveButton("확인", listener)
                builder.setNegativeButton("취소", listener)
                builder.show()
            }
        }
    }
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {

            }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PHONE_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(context, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}