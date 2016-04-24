package jstack.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import jstack.pid.PidProvider

/**
 * Created by wannabe on 24.04.16.
 */
class JStackAction: AnAction() {
	override fun actionPerformed(e: AnActionEvent?) {
		var pid = PidProvider.getProcessPid()
		println("Performed ${pid.id} ${pid.name}")
	}
}