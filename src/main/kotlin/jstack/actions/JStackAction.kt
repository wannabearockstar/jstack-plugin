package jstack.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by wannabe on 24.04.16.
 */
class JStackAction: AnAction() {
	override fun actionPerformed(e: AnActionEvent?) {
		println("Performed")
	}
}