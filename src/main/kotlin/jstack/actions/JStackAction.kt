package jstack.actions;

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import jstack.form.ThreadTableDialog
import jstack.pid.PidProvider
import jstack.system.SystemHandler

/**
 * Created by wannabe on 24.04.16.
 */
class JStackAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent?) {
        val pid = PidProvider.getProcessPid()
        val threads = SystemHandler.getThreadDump(pid.id) ?: return
        val table = ThreadTableDialog(threads)
        table.pack()
        table.isVisible = true
    }
}