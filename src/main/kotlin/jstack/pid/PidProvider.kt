package jstack.pid

import com.intellij.openapi.ui.Messages
import com.sun.tools.attach.VirtualMachine
import com.sun.tools.attach.VirtualMachineDescriptor

/**
 * Created by vChiper on 4/24/2016.
 */

data class Pid(val id: Long, val name: String)

object PidProvider {

    fun getProcessPid(): Pid {
        var descrs = VirtualMachine.list()

        var names = descrs
                .map { it -> getProcessName(it) }
                .toTypedArray()

        var selected = Messages.showEditableChooseDialog(
                "Select process",
                "Select process",
                null,
                names,
                names.first(),
                null)

        var descr = descrs.first { it -> it.displayName().contains(selected ?: "", true) };

        return Pid(descr.id()?.toLong() ?: -1, descr.displayName() ?: "");
    }

    private fun getProcessName(descr: VirtualMachineDescriptor): String {

        var name = descr.displayName();

        if (name.isEmpty())
            return name

        var parts = name.split(' ');
        if (parts.count() == 0)
            return name

        return parts[0];
    }
}