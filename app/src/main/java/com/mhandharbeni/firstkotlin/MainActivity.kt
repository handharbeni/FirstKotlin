package com.mhandharbeni.firstkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnTextChanged
import io.realm.Realm
import io.realm.kotlin.where
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    @BindView(R.id.rvCustomer)
    lateinit var rvCustomer: RecyclerView

    @BindView(R.id.search)
    lateinit var search: EditText

    val TAG : String = "KOTLIN DB"

    private var adapter : CustomAdapter? = null

    private lateinit var realm: Realm
    private val customerList = ArrayList<Customer>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()

        setContentView(R.layout.activity_main)
        butterknife.ButterKnife.bind(this)

        insert()
        read()
        initRV()
    }
    fun initRV(){
        rvCustomer.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = CustomAdapter(customerList) { partItem : Customer -> onCustomerClick(partItem) }
        rvCustomer.adapter = adapter
    }
    fun insert(){
        for (i in 1..10){
            realm.executeTransaction {
                val customer = Customer()
                customer.id = i.toLong()
                customer.name = "name$i"
                customer.email = "email$i"
                it.copyToRealmOrUpdate(customer)
            }
        }
    }

    private fun read(){
        for (customer in realm.where<Customer>().findAll()){
            customerList.add(customer)
        }
    }

    private fun exeSearch(search: String){
        customerList.clear()
        for(customer in realm.where<Customer>().contains("name", search).or().contains("email", search).findAll()){
            customerList.add(customer)
        }
        initRV()
    }

    private fun onCustomerClick(customer : Customer){
        toast(customer.name)
    }

    @OnTextChanged(R.id.search)
    fun doUpdate(){
        exeSearch(search.text.toString())
    }
}
