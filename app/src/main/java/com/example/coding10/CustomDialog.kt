import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.coding10.R
import com.example.coding10.databinding.ActivityMainBinding
import com.example.coding10.databinding.DialogLayoutBinding


class CustomDialog(context: Context) : DialogFragment() {
    private val dialog = Dialog(context)
    private lateinit var binding: DialogLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogLayoutBinding.inflate(inflater, container, false)

        // 추가 데이터 변수
        var dialog_name = binding.dialogName
        var dialog_number = binding.dialogNumber
        var dialog_email = binding.dialogEmail
        var dialog_event = binding.dialogEvent


//        dialog?.window?.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
//        dialog?.setCancelable(true)

        binding.dialogCancel.setOnClickListener {
            dismiss()
        }

        binding.dialogSave.setOnClickListener {

            if (dialog_name.toString().trim().isEmpty()) {
                Toast.makeText(requireContext(),"이름을 입력하세요.",Toast.LENGTH_SHORT).show()
            } else if (dialog_number.toString().trim().isEmpty()){
                Toast.makeText(requireContext(),"번호을 입력하세요.",Toast.LENGTH_SHORT).show()
            } else if (dialog_email.toString().trim().isEmpty()){
                Toast.makeText(requireContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show()
            } else if (dialog_event.toString().trim().isEmpty()){
                Toast.makeText(requireContext(),"이벤트를 입력하세요.",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(requireContext(),"회원가입 완료.",Toast.LENGTH_SHORT).show()
            }

            dismiss()
        }

        return binding.root
    }
}


