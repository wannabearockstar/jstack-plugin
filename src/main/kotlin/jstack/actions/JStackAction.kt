package jstack.actions;

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressManager
import jstack.form.ThreadTableDialog
import jstack.pid.PidProvider
import jstack.system.SystemHandler

/**
 * Created by wannabe on 24.04.16.
 */
class JStackAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent?) {

        val project = e?.project ?: return
        val pid = PidProvider.getPid() ?: return

        val threads = ProgressManager.getInstance()
                .run(SystemHandler.getThreadDumpTask(project, pid.id))

        val table = ThreadTableDialog(threads)
        table.pack()
        table.isVisible = true
    }
}