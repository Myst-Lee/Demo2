package my.edu.tarc.demo2.scanin

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.demo2.databinding.ActivityScanInBinding
import my.edu.tarc.demo2.ui.model.Inventory

class ScanInActivity : AppCompatActivity() {
      private lateinit var appBarConfiguration: AppBarConfiguration
      private lateinit var binding: ActivityScanInBinding
      private lateinit var db: DatabaseReference

    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var ref: DatabaseReference

    lateinit var editTextSerialNumber: TextView
    lateinit var editTextPartNo: TextView
    lateinit var editTextQtyInput: EditText
    lateinit var editTextRackIn: TextView
    lateinit var editTextRackOut: TextView
    lateinit var editTextRackId: TextView
    lateinit var inventoryList: MutableList<Inventory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Inventory")
        inventoryList = mutableListOf()
    }

       private fun readData() {

            val database: DatabaseReference
            database = Firebase.database.reference

           val dbase = Firebase.database
           val myRef = dbase.getReference("inventory")

            val reference = FirebaseDatabase.getInstance().getReference("inventory")

                reference.child("Serial Number").get().addOnCompleteListener(OnCompleteListener {
                    fun onComple(task: Task<DataSnapshot>) {
                        if (task.isSuccessful) {
                            val dataSnapShot: DataSnapshot = task.getResult()
                            binding.textView15.setText(dataSnapShot.child("Serial Number").getValue().toString())
                            binding.textView18.setText(dataSnapShot.child("Part Number").getValue().toString())
                            binding.textView10.setText(dataSnapShot.child("Rack in date").getValue().toString())
                            binding.textView16.setText(dataSnapShot.child("Rack Out Date").getValue().toString())
                            binding.textView9.setText(dataSnapShot.child("Rack id").getValue().toString())

                            }

                            val textViewSerialNumber = binding.textView15
                            val textViewPartNo = binding.textView18
                            val textViewQtyInput = binding.editTextQtyInput
                            val textViewRackIn = binding.textView10
                            val textViewRackOut = binding.textView16
                            val textViewRackId = binding.textView9

                            binding.buttonSubmit.setOnClickListener {

                                if (binding.editTextQtyInput.text.isEmpty()) {
                                    binding.editTextQtyInput.error = "Value Required"
                                    return@setOnClickListener
                                }

                                val qty_input: Int = Integer.parseInt(binding.editTextQtyInput.text.toString())
                            }

                           fun onCancelled(error: DatabaseError) {
                                Log.w("Failed to read value.", error.toException())
                            }
                        }

                    })
                }
}
