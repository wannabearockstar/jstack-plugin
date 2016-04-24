package jstack.form

import com.intellij.openapi.ui.DialogWrapper
import jstack.pid.Pid
import javax.swing.JComboBox
import javax.swing.JComponent

/**
 * Created by vChiper on 4/24/2016.
 */
open class PidDialog : DialogWrapper {

    val processes: JComboBox<Pid>

    var pid: Pid? = null
        get
        private set

    constructor(pids: Array<Pid>) : super(true) {

        processes = JComboBox(pids)
        processes.isEditable = false
        processes.addActionListener({ e ->
            val src = e?.source as? JComboBox<*>
            pid = src?.selectedItem as? Pid
        })

        init()
        title = "Select process"
    }

    override fun createCenterPanel(): JComponent? {
        return processes
    }
}