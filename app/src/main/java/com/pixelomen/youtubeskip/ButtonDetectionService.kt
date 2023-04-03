package com.pixelomen.youtubeskip

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log


class ButtonDetectionService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            val source = event.source
            if (source != null) {
                performActionOnButton(source)
            }
        }
    }

    private fun performActionOnButton(nodeInfo: AccessibilityNodeInfo) {
        val targetButtonId = "com.google.android.youtube:id/skip_ad_button_container"
        val buttonNodes = nodeInfo.findAccessibilityNodeInfosByViewId(targetButtonId)

//        val nodeListString = buttonNodes.joinToString(separator = "\n") { node ->
//            // Return a string representation of each node
//            "Node: $node"
//        }
//
//        Log.i("TAG", "Node list:\n$nodeListString")

        if (buttonNodes != null && buttonNodes.isNotEmpty()) {
            for (node in buttonNodes) {
                if (node.isEnabled && node.isVisibleToUser) {
                    val parent = node.parent
                    val clickSuccess = parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    Log.i("MYAPP", clickSuccess.toString())
                    break
                }
            }
        }
    }

    override fun onInterrupt() {
        return
    }

}