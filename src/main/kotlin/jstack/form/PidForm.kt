package jstack.form

import com.intellij.openapi.ui.DialogWrapper
import jstack.pid.Pid
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Created by vChiper on 4/24/2016.
 */
open class PidForm : DialogWrapper {

    private var panel: JPanel? = null
    private var processes: JComboBox<Pid>? = null
    private var listener: ((Pid) -> Unit)? = null

    constructor(pids: Array<Pid>) : super(true) {
        processes = JComboBox(pids)
        processes?.isEditable = false
        processes?.addActionListener({ e ->
            val src = e?.source as? JComboBox<*>
            val pid = src?.selectedItem as? Pid

            if (pid !== null)
                listener?.invoke(pid)
        })
    }

    fun addPidListener(listener: (Pid) -> Unit) {
        this.listener = listener
    }

    override fun createCenterPanel(): JComponent? {
        return panel
    }
}