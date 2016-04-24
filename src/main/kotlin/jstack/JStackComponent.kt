package main.kotlin.jstack

import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project

/**
 * Created by wannabe on 23.04.16.
 */
class JStackComponent(val project: Project) : ProjectComponent {
    override fun getComponentName(): String = "JStackComponent"

    override fun disposeComponent() {
        println("dispose")
    }

    override fun initComponent() {
        println("init")
    }

    override fun projectClosed() {
        println("closed")
    }

    override fun projectOpened() {
        println("opened")
    }
}