package edu.ap.mobiledevelopmentproject.ui.matches
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.ap.mobiledevelopmentproject.R
import java.util.Calendar
import java.util.Locale

class MatchesFragment : Fragment() {

    private lateinit var timePicker: TimePicker
    private lateinit var datePicker: DatePicker
    private lateinit var bookButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)

        // initialisation time picker
        timePicker = view.findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)

        // initialisation date picker
        datePicker = view.findViewById(R.id.datePicker)

        // initialisation location spinner
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val locations = resources.getStringArray(R.array.locations_list)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        bookButton = view.findViewById(R.id.bookButton)
        bookButton.setOnClickListener {
            showConfirmation()
        }

        return view
    }

    private fun showConfirmation() {
        val selectedMonth = datePicker.month + 1
        val selectedDay = datePicker.dayOfMonth

        val startHour = timePicker.hour
        val startMinute = timePicker.minute

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, startHour)
            set(Calendar.MINUTE, startMinute)
            add(Calendar.MINUTE, 90) // Adding 90 minutes
        }

        val endHour = calendar.get(Calendar.HOUR_OF_DAY)
        val endMinute = calendar.get(Calendar.MINUTE)

        val location = requireView().findViewById<Spinner>(R.id.spinner).selectedItem.toString()
        val startTime = String.format(Locale.getDefault(), "%02d:%02d", startHour, startMinute)
        val endTime = String.format(Locale.getDefault(), "%02d:%02d", endHour, endMinute)
        val date = String.format(Locale.getDefault(), "%02d/%02d", selectedDay, selectedMonth)

        val confirmationText = "You booked at $location, the $date at $startTime until $endTime"
        Toast.makeText(requireContext(), confirmationText, Toast.LENGTH_SHORT).show()
    }

}