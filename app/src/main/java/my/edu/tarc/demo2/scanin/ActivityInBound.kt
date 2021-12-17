package my.edu.tarc.demo2.scanin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import my.edu.tarc.demo2.R
import my.edu.tarc.demo2.databinding.ActivityInBoundBinding
import my.edu.tarc.demo2.ui.model.Inventory


class ActivityInBound : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInBoundBinding
    private lateinit var db: DatabaseReference

    private lateinit var auth: FirebaseAuth
    private var databaseReference :  DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private lateinit var ref: DatabaseReference

    lateinit var editTextSerialNo : TextView
    lateinit var editTextPartNo: TextView
    lateinit var editTextQtyInput: EditText
    lateinit var editTextRackIn: TextView
    lateinit var editTextRackOut: TextView
    lateinit var editTextRackId: TextView
    private lateinit var inventoryList: MutableList<Inventory>
    private lateinit var inventoryList1: ArrayList<Inventory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInBoundBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_in_bound)
        val root: View = binding.root

        ref = FirebaseDatabase.getInstance().getReference("inventory")
        inventoryList1 = arrayListOf()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Inventory")
        inventoryList = mutableListOf()
    }

    private fun readData(series_no :String) {

//        val database: DatabaseReference
//        database = Firebase.database.reference
//        val dbase = Firebase.database
//        val myRef = dbase.getReference("inventory")

        databaseReference = FirebaseDatabase.getInstance().getReference("inventory")
        databaseReference!!.child(series_no).get().addOnSuccessListener {
            if(it.exists()){
                val serial_no = it.child("Serial Number").value
                val part_no = it.child("Part Number").value
                val date_rackIn = it.child("Rack in date").value
                val date_rackOut = it.child("Rack Out Date").value
                val rack_no = it.child("Rack Number").value

//                Toast.makeText(this.baseContext"Successful" , Toast.LENGTH_SHORT).show()

                binding.textViewSerialNo.text = serial_no.toString()
                binding.textViewPartNo.text = part_no.toString()
                binding.textViewRackNo.text = rack_no.toString()
                binding.textViewRackIn.text = date_rackIn.toString()
                binding.textViewRackOut.text = date_rackOut.toString()
            }

        }
        
                binding.buttonSubmit.setOnClickListener {

                    val textViewSerialNumber = binding.textView19
                    val textViewPartNo = binding.textView17
                    val textViewQtyInput = binding.editTextQtyInput
                    val textViewRackIn = binding.textView21
                    val textViewRackOut = binding.textView22
                    val textViewRackId = binding.textView20

                    if (binding.editTextQtyInput.text.isEmpty()) {
                        binding.editTextQtyInput.error = "Value Required"
                        return@setOnClickListener
                    }

                    val qty_input: Int = Integer.parseInt(binding.editTextQtyInput.text.toString())

                }

//        ref = FirebaseDatabase.getInstance().getReference().child("inventory")
//        ref.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot!!.exists()){
//                    inventoryList1.clear()
//                    for (i in snapshot.children){
//                        val value = i.getValue(Inventory::class.java)
//                        inventoryList.add(value!!)
//                    }
//
//                }
//            }
//
//        override fun onCancelled(error: DatabaseError) {
//                Log.w("Failed to read value.", error.toException())
//
//            }

//        })

        }

    override fun onDestroy() {
        super.onDestroy()
    }

}

