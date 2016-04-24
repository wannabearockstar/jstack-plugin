package jstack.pid

import com.sun.tools.attach.VirtualMachine
import com.sun.tools.attach.VirtualMachineDescriptor
import jstack.form.PidDialog

/**
 * Created by vChiper on 4/24/2016.
 */
object PidProvider {

    fun getPid() : Pid? {
        val dialog = PidDialog(
            VirtualMachine.list()
                .map { it -> getPid(it) }
                .toTypedArray()
        )

        return if (dialog.showAndGet()) dialog.pid else null
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