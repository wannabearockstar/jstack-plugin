package jstack.pid

import com.sun.tools.attach.VirtualMachine
import com.sun.tools.attach.VirtualMachineDescriptor
import jstack.form.PidForm

/**
 * Created by vChiper on 4/24/2016.
 */
object PidProvider {

    fun getProcessPid(listener: (Pid) -> Unit) {
        val pids = VirtualMachine.list()
                .map { it -> getPid(it) }
                .toTypedArray()

        val form = PidForm(pids)
        form.addPidListener(listener)
    }

    private fun getPid(descriptor: VirtualMachineDescriptor) : Pid {

        val name = descriptor.displayName();

        if (name.isEmpty())
            return Pid(descriptor.id().toLong())

        val parts = name.split(' ');

        if (parts.count() == 0)
            return Pid(descriptor.id().toLong())

        return Pid(descriptor.id().toLong(), parts[0]);
    }
}