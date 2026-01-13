package com.example.tiket.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tiket.Domain.FlightModel
import com.example.tiket.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadLocation(): LiveData<MutableList<LocationModel>> {
        val listData = MutableLiveData<MutableList<LocationModel>>()
        val ref = firebaseDatabase.getReference("Locations")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<LocationModel>()
                for (childSnapshot in snapshot.children) {
                    val location = childSnapshot.getValue(LocationModel::class.java)
                    location?.let { list.add(it) }
                }
                listData.value = list
            }
            override fun onCancelled(error: DatabaseError) {
                listData.value = null
            }
        })
        return listData
    }

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        val listData = MutableLiveData<MutableList<FlightModel>>()
        val ref = firebaseDatabase.getReference("Flights")
        val query: Query = ref.orderByChild("from").equalTo(from)

        Log.d("FlightRepository", "loadFiltered: Starting query with from='$from', to='$to'")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("FlightRepository", "onDataChange: Received ${snapshot.childrenCount} flights from Firebase")

                val lists = mutableListOf<FlightModel>()
                for (childSnapshot in snapshot.children) {
                    Log.d("FlightRepository", "=== RAW DATA ===")
                    Log.d("FlightRepository", childSnapshot.value.toString())
                    Log.d("FlightRepository", "================")

                    val list = childSnapshot.getValue(FlightModel::class.java)
                    if (list != null) {
                        Log.d("FlightRepository", "Processing flight: From=${list.from}, To=${list.to}")
                        Log.d("FlightRepository", "ArilineLogo='${list.airlineLogo}', ArilineName='${list.airlineName}'")

                        if (list.to == to) {
                            lists.add(list)
                            Log.d("FlightRepository", "Flight matched filter - added to list")
                        } else {
                            Log.d("FlightRepository", "Flight doesn't match destination filter - skipped")
                        }
                    } else {
                        Log.w("FlightRepository", "Null flight data at key: ${childSnapshot.key}")
                    }
                }

                Log.d("FlightRepository", "loadFiltered: Total filtered flights = ${lists.size}")
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FlightRepository", "loadFiltered: Firebase query cancelled", error.toException())
                listData.value = mutableListOf()
            }
        })

        return listData
    }
}