import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.PatternMatcher
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.coding10.MainItems
import com.example.coding10.R
import com.example.coding10.databinding.ActivityMainBinding
import com.example.coding10.databinding.DialogLayoutBinding
import com.example.coding10.getUri
import java.util.regex.Pattern


class CustomDialog(
    //리스트에 추가해 줄 생성자
    val onSave: (item: MainItems) -> Unit

) : DialogFragment() {
    //    private val dialog = Dialog(context)
    private lateinit var binding: DialogLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogLayoutBinding.inflate(inflater, container, false)

        // 추가할 데이터 변수
        var dialog_name = binding.dialogName
        var dialog_number = binding.dialogNumber
        var dialog_email = binding.dialogEmail
        var dialog_memo = binding.dialogMemo
        var dialog_age = binding.dialogAge
        var dialog_bloodtype = binding.dialogBloodType

//        //다이얼로그 크기조절
//        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT)
//
//        // dialog 바깥쪽 클릭 시 dialog 종료
//        dialog?.setCancelable(true)

        // Cancel 버튼
        binding.dialogCancel.setOnClickListener {
            dismiss()
        }

        // Save 버튼
        binding.dialogSave.setOnClickListener {
            val dialog_name = dialog_name.text
            val dialog_number = dialog_number.text
            val dialog_email = dialog_email.text
            val dialog_memo = dialog_memo.text
            val dialog_age = dialog_age.text
            val dialog_bloodtype = dialog_bloodtype.text

                if (dialog_name.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
                } else if (!Pattern.matches("^[가-힣]*\$", dialog_name)) {
                    // 정렬을 한글로 해서 한글 이름 입력으로 했습니다.
                    Toast.makeText(requireContext(), "한글 이름으로 입력해 주세요.", Toast.LENGTH_SHORT).show()
                } else if (dialog_number.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "휴대폰 번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                } else if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", dialog_number)) {
                    Toast.makeText(requireContext(), "올바른 번호가 아닙니다.\n 예시:<***-****-****>", Toast.LENGTH_SHORT).show()
                } else if (dialog_email.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(dialog_email).matches()) {
                    var textemail =
                        Toast.makeText(requireContext(), "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT)
                    textemail.show()
                } else if (dialog_memo.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "하고 싶은 말을 적어주세요.", Toast.LENGTH_SHORT).show()
                } else if (dialog_age.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "나이를 입력하세요", Toast.LENGTH_SHORT).show()
                } else if (!Pattern.matches("^[0-7]$", dialog_age)) {
                    Toast.makeText(requireContext(), "만 7세 이하만 가입이 가능합니다.", Toast.LENGTH_SHORT).show()
                } else if (dialog_bloodtype.toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "혈액형을 입력하세요", Toast.LENGTH_SHORT).show()
                } else if (!Pattern.matches("^[a-zA-Z]*\$", dialog_bloodtype)) {
                    Toast.makeText(requireContext(), "올바른 혈액형을 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()

                // 입력한 데이터 MainItems에 추가하고 onSave를 통해 데이터 뿌려주기
                val contact = MainItems(
                    getUri(R.drawable.baby1), getUri(R.drawable.baby1_2),
                    aName = dialog_name.toString(), aNumber = dialog_number.toString(),
                    aEmail = dialog_email.toString(), aMemo = dialog_memo.toString(),
                    //처음 다이얼로그에서 입력한 Edit는 4개였지만 MainItems(더미데이터)의 데이터는
                    //aName,aNumber,aEmail,aMemo,aAge,aBloodType 총 6개 이므로 아래 2개를 추가
                    aAge = dialog_age.toString(), aBloodType = dialog_bloodtype.toString()
                )
                onSave(contact)
                dismiss()
            }
        }

        return binding.root
    }
}


