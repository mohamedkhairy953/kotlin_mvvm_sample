
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/**
 * Created by khairy on خ, 23/ماي/2019 at 09:29 ص.
 * mohamed.khairy@apptcom.com
 */
open class BaseFragment(@LayoutRes val layoutRes: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }
}