package jstack.system

import com.sun.tools.attach.VirtualMachine

import java.io.File
import java.lang.management.ManagementFactory
import java.lang.management.ThreadInfo
import java.lang.management.ThreadMXBean
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL
/**
 * Created by wannabe on 24.04.16.
 */
object SystemHandler {
    val CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress"

    fun getThreadDump(pid: Long): Map<Long, ThreadInfo>? {
        val threadMaxBean = getThreadMXBean(pid) ?: return null
        return threadMaxBean.getThreadInfo(threadMaxBean.allThreadIds)
                .groupBy { it.threadId }
                .map { it.key to  it.value[0] }
                .toMap<Long, ThreadInfo>()
    }

    private fun getThreadMXBean(pid: Long): ThreadMXBean? {
        val vmD = VirtualMachine.list().find { it.id().toLong() === pid } ?: return null
        val a = extractJMXServiceURL(vmD.id().toInt())
        val con = JMXConnectorFactory.connect(a).mBeanServerConnection
        return ManagementFactory.newPlatformMXBeanProxy(con, ManagementFactory.THREAD_MXBEAN_NAME, ThreadMXBean::class.java)
    }

    private fun extractJMXServiceURL(pid: Int): JMXServiceURL {
        val vm = VirtualMachine.attach(pid.toString());
        try {
            var connectorAddress = vm.agentProperties.getProperty(CONNECTOR_ADDRESS);
            if (connectorAddress == null) {
                val agent = vm.systemProperties.getProperty("java.home") +
                        File.separator + "lib" + File.separator +
                        "management-agent.jar";
                vm.loadAgent(agent);
                connectorAddress = vm.agentProperties.getProperty(CONNECTOR_ADDRESS);
            }
            return JMXServiceURL(connectorAddress);
        } finally {
            vm.detach()
        }
    }

}