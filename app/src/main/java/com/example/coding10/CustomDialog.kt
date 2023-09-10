import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PatternMatcher
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.DialogFragment
import com.android.contectapp.Notification
import com.example.coding10.MainActivity
import com.example.coding10.MainItems
import com.example.coding10.R
import com.example.coding10.databinding.DialogLayoutBinding
import com.example.coding10.getUri
import com.google.android.material.snackbar.Snackbar
import java.sql.Time
import java.text.DateFormat
import java.util.Calendar
import java.util.regex.Pattern

@SuppressLint("MissingInflatedId")
class CustomDialog(
    //리스트에 추가해 줄 생성자
    val onSave: (item: MainItems) -> Unit


) : DialogFragment() {
    //    private val dialog = Dialog(context)
    private lateinit var binding: DialogLayoutBinding

    //notification 알람
    private val Notification: Notification by lazy {
        Notification(requireContext())
    }
    private val channelId = "my_channel_id"
    private val notificationId = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLayoutBinding.inflate(inflater, container, false)

        //Event 시간에 맞춰 Notification 표시
        binding.dialogNotification1.setOnClickListener {

            createNotificationChannel()
            val delayMillis: Long = 5000
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

            // 5초 후에 알림 생성
            binding.dialogNotification1.postDelayed({
                val notificationBuilder =
                    context?.let { it1 ->
                        NotificationCompat.Builder(it1, channelId)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("알림 제목")
                            .setContentText("알림 내용")
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                    }

                val notificationManager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (notificationBuilder != null) {
                    notificationManager.notify(notificationId, notificationBuilder.build())
                }

                Toast.makeText(context, "5분 후에 알림이 표시됩니다.", Toast.LENGTH_SHORT).show()
            }, delayMillis)

        }
        binding.dialogNotification2.setOnClickListener {
            createNotificationChannel()
            val delayMillis: Long = 10000
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

            // 10초 후에 알림 생성
            binding.dialogNotification1.postDelayed({
                val notificationBuilder =
                    context?.let { it1 ->
                        NotificationCompat.Builder(it1, channelId)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("알림 제목")
                            .setContentText("알림 내용")
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                    }

                val notificationManager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (notificationBuilder != null) {
                    notificationManager.notify(notificationId, notificationBuilder.build())
                }

                Toast.makeText(context, "10분 후에 알림이 표시됩니다.", Toast.LENGTH_SHORT).show()
            }, delayMillis)
        }


        // Cancel 버튼
        binding.dialogCancel.setOnClickListener {
            dismiss()
        }

        // 추가할 데이터 변수
        var dialogName = binding.dialogName
        var dialogNumber = binding.dialogNumber
        var dialogEmail = binding.dialogEmail
        var dialogMemo = binding.dialogMemo
        var dialogAge = binding.dialogAge
        var dialogBloodType = binding.dialogBloodType

        var dialogNameError = binding.dialogNameError
        var dialogNumberError = binding.dialogNumberError
        var dialogEmailError = binding.dialogEmailError
        var dialogAgeError = binding.dialogAgeError
        var dialogBloodtypeError = binding.dialogBloodError

        with(binding) {// 순서 - 이름, 번호, 메일, 나이, 피
            dialogName.addTextChangedListener(createNameTextWatcher(dialogName))
            dialogNumber.addTextChangedListener(createNumberTextWatcher(dialogNumber))
            dialogEmail.addTextChangedListener(createMailTextWatcher(dialogEmail))
            dialogAge.addTextChangedListener(createAgeTextWatcher(dialogAge))
            dialogBloodType.addTextChangedListener(createBloodTypeTextWatcher(dialogBloodType))
        }
        // Save 버튼
        binding.dialogSave.setOnClickListener {


//                if (dialog_name.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
//                } else if (!Pattern.matches("^[가-힣]*\$", dialog_name)) {
//                    // 정렬을 한글로 해서 한글 이름 입력으로 했습니다.
//                    Toast.makeText(requireContext(), "한글 이름으로 입력해 주세요.", Toast.LENGTH_SHORT).show()
//                } else if (dialog_number.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "휴대폰 번호를 입력하세요.", Toast.LENGTH_SHORT).show()
//                } else if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", dialog_number)) {
//                    Toast.makeText(requireContext(), "올바른 번호가 아닙니다.\n 예시:<***-****-****>", Toast.LENGTH_SHORT).show()
//                } else if (dialog_email.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show()
//                } else if (!Patterns.EMAIL_ADDRESS.matcher(dialog_email).matches()) {
//                    var textemail =
//                        Toast.makeText(requireContext(), "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT)
//                    textemail.show()
//                } else if (dialog_memo.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "하고 싶은 말을 적어주세요.", Toast.LENGTH_SHORT).show()
//                } else if (dialog_age.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "나이를 입력하세요", Toast.LENGTH_SHORT).show()
//                } else if (!Pattern.matches("^[0-7]$", dialog_age)) {
//                    Toast.makeText(requireContext(), "만 7세 이하만 가입이 가능합니다.", Toast.LENGTH_SHORT).show()
//                } else if (dialog_bloodtype.toString().trim().isEmpty()) {
//                    Toast.makeText(requireContext(), "혈액형을 입력하세요", Toast.LENGTH_SHORT).show()
//                } else if (!Pattern.matches("^[a-zA-Z]*\$", dialog_bloodtype)) {
//                    Toast.makeText(requireContext(), "올바른 혈액형을 입력해주세요.", Toast.LENGTH_SHORT).show()
//                } else {
//                Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()
//
//                // 입력한 데이터 MainItems에 추가하고 onSave를 통해 데이터 뿌려주기
//                val contact = MainItems(
//                    getUri(R.drawable.baby1), getUri(R.drawable.baby1_2),
//                    aName = dialog_name.toString(), aNumber = dialog_number.toString(),
//                    aEmail = dialog_email.toString(), aMemo = dialog_memo.toString(),
//                    //처음 다이얼로그에서 입력한 Edit는 4개였지만 MainItems(더미데이터)의 데이터는
//                    //aName,aNumber,aEmail,aMemo,aAge,aBloodType 총 6개 이므로 아래 2개를 추가
//                    aAge = dialog_age.toString(), aBloodType = dialog_bloodtype.toString()
//                )
//                onSave(contact)
//                dismiss()
//            }

            // 조건 추가하기 ( not null + @ )
            if (dialogNameError.visibility == View.INVISIBLE &&
                dialogNumberError.visibility == View.INVISIBLE &&
                dialogEmailError.visibility == View.INVISIBLE &&
                dialogAgeError.visibility == View.INVISIBLE &&
                dialogBloodtypeError.visibility == View.INVISIBLE &&
                dialogName.text.toString().trim().isNotEmpty() &&
                dialogNumber.text.toString().trim().isNotEmpty() &&
                dialogEmail.text.toString().trim().isNotEmpty() &&
                dialogAge.text.toString().trim().isNotEmpty() &&
                dialogBloodType.text.toString().trim().isNotEmpty()
            ) {
                Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()

//                myPageEtBlood.text = Editable.Factory.getInstance().newEditable(hashMap["혈액형"])
//                hashMap["혈액형"] = myPageEtBlood.text.toString()
//
//                binding.dialogName
//                Editable.Factory.getInstance().newEditable(hashMap["혈액형"])

                // 입력한 데이터 MainItems에 추가하고 onSave를 통해 데이터 뿌려주기
                val contact = MainItems (
                    getUri(R.drawable.baby1), getUri(R.drawable.baby1_2),
                    aName = binding.dialogName.text.toString(), aNumber = binding.dialogNumber.text.toString(),
                    aEmail = binding.dialogEmail.text.toString(), aMemo = binding.dialogMemo.text.toString(),
                    //처음 다이얼로그에서 입력한 Edit는 4개였지만 MainItems(더미데이터)의 데이터는
                    //aName,aNumber,aEmail,aMemo,aAge,aBloodType 총 6개 이므로 아래 2개를 추가
                    aAge = binding.dialogAge.text.toString(), aBloodType = binding.dialogBloodType.text.toString()

                )
                onSave(contact)
                dismiss()

            } else {
                Snackbar.make(binding.root, "입력 값을 확인해주세요.", Snackbar.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }

    //dialog_name
    private fun createNameTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern = Regex("\\b[가-힣]{2,}\\b")
                    if (id.isNotEmpty() && regexPattern.matches(id)) {
                        dialogNameError.visibility = View.INVISIBLE
                    } else {
                        dialogNameError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    //dialog_number
    private fun createNumberTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) =
                with(binding) {
                    val regexPatternLength = Regex("^.{1,13}\$")
                    val id = s.toString()
                    val regexPattern1 = Regex("^\\d{3}-\\d{4}-\\d{4}$")
                    val regexPattern2 = Regex("^[A-Za-z]")
                    if (regexPattern1.matches(id) && id.isNotEmpty() && regexPatternLength.matches(
                            id
                        )
                    ) {
                        dialogNumberError.visibility = View.INVISIBLE
                    } else if (regexPatternLength.matches(id)) {
                        dialogNumberError.visibility = View.INVISIBLE
                    } else if (regexPattern2.matches(id)) {
                        dialogNumberError.visibility = View.VISIBLE
                    } else {
                        dialogNumberError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    //dialog_email
    private fun createMailTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) =
                with(binding) {

                    val id = s.toString()
                    val emailRegexPattern = Regex("^[A-Za-z0-9+_.-]{1,12}+@(.+)\$")
                    val regexPatternLength = Regex("^.{1,13}\$")

                    if (id.isNotEmpty() && emailRegexPattern.matches(id)) {
                        dialogEmailError.visibility = View.INVISIBLE
                    } else {
                        dialogEmailError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    //dialog_age
    private fun createAgeTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) =
                with(binding) {

                    val id = s.toString()
                    val regexPattern = Regex("[1-7]")

                    if (id.isNotEmpty() && regexPattern.matches(id)) {

                        dialogAgeError.visibility = View.INVISIBLE

                    } else {

                        dialogAgeError.visibility = View.VISIBLE

                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    //dialog_blood_type
    private fun createBloodTypeTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) =
                with(binding) {

                    val id = s.toString()
                    val regexPatternLength = Regex("^.{1,2}\$")
                    val inputText = Regex("[ABOabo]+")

                    if (id.isNotEmpty() && inputText.matches(id) && regexPatternLength.matches(
                            id
                        )
                    ) {
                        dialogBloodError.visibility = View.INVISIBLE
                    } else {
                        dialogBloodError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    private fun scheduleNotification(delayMillis: Long, message: String) {
        // 지연 후 알림 생성 및 표시
        val notificationBuilder = Notification.createNotification("알림이 예약되었습니다", message)
        Notification.showNotification(1, notificationBuilder)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}
