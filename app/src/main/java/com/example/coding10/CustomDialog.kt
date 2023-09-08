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
    private var hashMap = HashMap<String, String>()
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
        val binding = DialogLayoutBinding.inflate(inflater, container, false)

        // 추가할 데이터 변수
        var dialog_name = binding.dialogName
        var dialog_number = binding.dialogNumber
        var dialog_email = binding.dialogEmail
        var dialog_memo = binding.dialogMemo
        var dialog_age = binding.dialogAge
        var dialog_bloodtype = binding.dialogBloodtype

        var dialog_name_error = binding.dialogNameError
        var dialog_number_error = binding.dialogPhoneError
        var dialog_email_error = binding.dialogEmailError
        var dialog_age_error = binding.dialogAgeError
        var dialog_bloodtype_error = binding.dialogBloodError


        //Event 시간에 맞춰 Notification 표시
        binding.dialogNotification1.setOnClickListener {

            createNotificationChannel()
            val delayMillis: Long = 5000
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

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

                Toast.makeText(context, "5초 후에 알림이 표시됩니다.", Toast.LENGTH_SHORT).show()
            }, delayMillis)

        }
        binding.dialogNotification2.setOnClickListener {
            createNotificationChannel()
            val delayMillis: Long = 10000
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

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

                Toast.makeText(context, "5초 후에 알림이 표시됩니다.", Toast.LENGTH_SHORT).show()
            }, delayMillis)
        }


        // Cancel 버튼
        binding.dialogCancel.setOnClickListener {
            dismiss()
        }

        // Save 버튼
        binding.dialogSave.setOnClickListener {

            if (dialog_name_error.visibility == View.INVISIBLE &&
                dialog_number_error.visibility == View.INVISIBLE &&
                dialog_email_error.visibility == View.INVISIBLE &&
                dialog_age_error.visibility == View.INVISIBLE &&
                dialog_bloodtype_error.visibility == View.INVISIBLE
            ) {
                init(2)
                infoProcess("edit")
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

            } else {
                Snackbar.make(binding.root, "입력 값을 확인해주세요.", Snackbar.LENGTH_SHORT).show()
            }

//            val dialog_name = dialog_name.text
//            val dialog_number = dialog_number.text
//            val dialog_email = dialog_email.text
//            val dialog_memo = dialog_memo.text
//            val dialog_age = dialog_age.text
//            val dialog_bloodtype = dialog_bloodtype.text
//
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
        }

        return binding.root
    }

    private fun scheduleNotification(delayMillis: Long, message: String) {
        // 지연 후 알림 생성 및 표시
        val notificationBuilder = Notification.createNotification("알림이 예약되었습니다", message)
        Notification.showNotification(1, notificationBuilder)
    }

    private fun createNameTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern1 = Regex("[가-힣]")

                    if (regexPattern1.matches(id) && id.isNotEmpty())
                     {
                        dialogNameError.visibility = View.INVISIBLE
                    }  else {
                        dialogNameError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
    private fun createEmailTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern1 = Regex ("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\$")


                    if (regexPattern1.matches(id) && id.isNotEmpty())
                    {
                        dialogEmailError.visibility = View.INVISIBLE
                    }  else {
                        dialogEmailError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
    private fun createphonTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern1 = Regex( "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")

                    if (regexPattern1.matches(id) && id.isNotEmpty() )
                    {
                        dialogPhoneError.visibility = View.INVISIBLE
                    }  else {
                        dialogPhoneError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
    private fun createAgeTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern1 = Regex("\"^[0-7]\$\"")

                    if (regexPattern1.matches(id) && id.isNotEmpty() )
                    {
                        dialogAgeError.visibility = View.INVISIBLE
                    }  else {
                        dialogAgeError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
    private fun createBloodTypeTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val id = s.toString()
                    val regexPattern1 = Regex("^[A-Za-z]")

                    if (regexPattern1.matches(id) && id.isNotEmpty() )
                    {
                        dialogBloodError.visibility = View.INVISIBLE
                    }  else {
                        dialogBloodError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
    private fun init(switch: Int) = with(binding) {

        when (switch) {
            1 -> {

                dialogName.isEnabled = true
                dialogAge.isEnabled = true
                dialogEmail.isEnabled = true
                dialogBloodtype.isEnabled = true
                dialogNumber.isEnabled = true

            }

            2 -> {
                dialogName.isEnabled = false
                dialogAge.isEnabled = false
                dialogEmail.isEnabled = false
                dialogBloodtype.isEnabled = false
                dialogNumber.isEnabled = false

            }

            else -> {

            }
        }
    }
    private fun infoProcess(data: String) = with(binding) {

        when (data) {
            "edit" -> {
                hashMap["이름"] = dialogName.text.toString()
                hashMap["혈액형"] = dialogBloodtype.text.toString()
                hashMap["번호"] = dialogNumber.text.toString()
                hashMap["이메일"] = dialogEmail.text.toString()
                hashMap["나이"] = dialogAge.text.toString()

//                hashMap["혈액형"]?.let { Log.d("test", it) }
            }

            "cancel" -> {

                dialogName.text = Editable.Factory.getInstance().newEditable(hashMap["이름"])
                dialogNumber.text = Editable.Factory.getInstance().newEditable(hashMap["번호"])
                dialogBloodtype.text = Editable.Factory.getInstance().newEditable(hashMap["혈액형"])
                dialogEmail.text = Editable.Factory.getInstance().newEditable(hashMap["이메일"])
                dialogAge.text = Editable.Factory.getInstance().newEditable(hashMap["나이"])

            }

            else -> {

            }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}



