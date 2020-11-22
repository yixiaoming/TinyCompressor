package org.yxm.tinycompressor.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class TinyCompressAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val path = project?.basePath?:"path is null"
        var dialog = CompressDialog(path)
        dialog.pack()
        dialog.isVisible = true
        dialog.setBounds(100, 100, 400, 800)
    }
}