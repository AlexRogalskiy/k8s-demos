import javafx.application.Application;
import javafx.application.Application.launch
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javax.swing.JFrame
import javax.swing.JEditorPane


private val htmlPage: String
    get() {
        val data = """
        <html>
<head>
<style>
body {
  margin: 0;
  font-family: Roboto, sans-serif;
  min-height: 100%;
  font-size: 13px;
}

h1 {
  font-weight: 600;
  font-size: 2.5em;
  color: color(textLink-foreground);
}

textarea:focus,
input:focus {
  outline: none;
}

a {
  text-decoration: none;
  cursor: pointer;

  &:not(.no-hover):hover {
    text-decoration: underline;
  }

  &.icon-link {
    display: flex;
    position: relative;

    .mat-icon {
      font-size: 1em;
      width: 1em;
      height: 1em;
      margin: .2em .2em 0;
    }

    &:hover {
      text-decoration: none;

      &::after {
        content: '';
        position: absolute;
        display: block;
        width: 100%;
        height: 100%;
        background: color(textLink-foreground);
        opacity: 0.2;
        border: 4px solid color(textLink-foreground);
        top: -.2em;
        left: -.2em;
        border-radius: 4px;
      }
    }
  }

}


// Layout classes
// page outer wrapper to center both vertically and horizontally
.cc-centered-page {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 3em);

}

// inner wrapper for page content
.cc-content {
  width: 90%;
  max-width: 1200px;
  margin: 3em auto;
}

.flex-row {
  display: flex;
  flex-direction: row;
  margin-bottom: 1em;

  &.centered {
    align-items: center;
  }

  p.list-item {
    margin: 0 0 1em;
  }

  .flex-fill {
    flex: 1 1 auto;
  }

  .flex-25 {
    width: 25%;
    max-width: 25%;
  }

  .flex-30 {
    width: 30%;
    max-width: 30%;
  }

  .flex-50 {
    width: 50%;
    max-width: 50%;
  }

  .flex-70 {
    width: 70%;
    max-width: 70%;
  }

  .flex-25,
  .flex-30,
  .flex-50,
  .flex-70 {
    margin: 0 2em;

    &:first-child {
      margin-left: 0;
    }

    &:last-child {
      margin-right: 0;
    }
  }
}

.item {
  margin: 1em 0 2.5em;

  .item-header {
    font-size: 1.2em;
    font-weight: 700;
  }

  p {
    margin: 0 0 0.5em;
  }

  &.clickable {
    position: relative;
    cursor: pointer;
    user-select: none;

    .item-header {
      position: relative;
      display: inline-block;
    }

    &:hover .item-header{
      color: color(textLink-foreground);

      &::after {
        content: '';
        position: absolute;
        left: 0;
        top: 0.9em;
        width: 100%;
        height: 0.5em;
        opacity: 0.2;
        background: color(textLink-foreground);
      }
    }

    &::before {
      content: '';
      position: absolute;
      right: 0;
      top: 0;
      width: 24px;
      height: 24px;
      opacity: 0.5;
      background: color(foreground);
      mask: url('./assets/arrow_forward.svg');
      transition: 200ms ease-in-out;
    }

    &:hover::before {
      opacity: 0.8;
      background: color(textLink-foreground);
    }
  }
}

ul.item {
  padding: 0 0 0 1.2em;

  li {
    position: relative;
    list-style-type: none;
    line-height: 1.8em;
  }

  li::before {
    content: '';
    position: absolute;
    left: -1.2em;
    top: 6px;
    width: 10px;
    height: 10px;
    opacity: 0.5;
    background: color(foreground);
    mask: url('./assets/arrow_right.svg');
  }
}

.heading {
  border-bottom: 1px solid color(textLink-foreground);
  display: flex;
  width: 100%;
  margin-bottom: 1.5em;

  h2 {
    border-bottom: 3px solid color(textLink-foreground);
    margin-bottom: -2px;
    line-height: 1.8em;
  }

  &.no-line {
    border-bottom: none;
  }
}

.subheading {
  display: flex;
  align-items: center;
  h3 {
    font-size: 16px;
    flex: 0 0 auto;
    margin: 0 1em 0 0;
  }

  p {
    margin: .5em 0;
  }

  &:after {
    content: '';
    display: block;
    height: 1px;
    flex: 1 1 auto;
    border-bottom: 1px solid color(textLink-foreground);
  }
}

.subhead-row {
  display: flex;
  cursor: pointer;

  p {
    margin: 1em 0;
    position: relative;
  }

  &.selected {
    p:before {
      content: '';
      position: absolute;
      display: block;
      background: color(textLink-foreground);
      opacity: .3;
      width: 100%;
      height: .5em;
      left: 0;
      bottom: 0;
      padding-right: 0.5em;
    }
  }
}

.action-row {
  display: flex;
  margin: 1.5em 0;
}

.column-title {
  font-size: 13px;
  color: color(textLink-foreground);
}

// Material component overrides
body.vscode-light,
body.vscode-dark,
body.vscode-high-contrast {

  .mat-progress-bar-buffer {
    opacity: 0.2;
  }

  .mat-accordion {

    .mat-expansion-panel {
      background: none;
      margin: 2em 0;

      &:not(.mat-expanded) {
        .mat-expansion-panel-header:not([aria-disabled=true]) {

          &.cdk-keyboard-focused,
          &.cdk-program-focused,
          &:hover {
            background: none;
          }
        }
      }

      &:first-of-type {
        border-top-right-radius: 0;
        border-top-left-radius: 0;
      }

      &:last-of-type {
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
      }

      .mat-expansion-panel-header {
        font-size: 13px;
        padding: 0 2px 0 0;

        .step-number {
          text-transform: uppercase;
          color: color(textLink-foreground);
        }

        .step-title-name {
          font-size: 1.3em;
        }
      }

      .mat-expansion-panel-header-description {
        align-self: center;
      }

      .mat-expansion-panel-body {
        padding: 1em 0
      }
    }

  }

  .mat-button,
  .mat-raised-button,
  .mat-flat-button,
  .mat-stroked-button,
  .mat-icon-button {
    font-size: 13px;
    font-weight: 700;
    margin: 0 0 0 1em;
    line-height: 2em;
    min-width: 90px;

    &.no-margin {
      margin: 0;
    }

    // Fix for ripple effect mixin not expecting color in var format.
    .mat-ripple-element {
      background-color: color(foreground);
      opacity: 0.1;
    }
  }

  .mat-checkbox-checked.mat-accent .mat-checkbox-background,
  .mat-checkbox-checked.mat-primary .mat-checkbox-background,
  .mat-checkbox-checked.mat-warn .mat-checkbox-background,
  .mat-checkbox-indeterminate.mat-accent .mat-checkbox-background,
  .mat-checkbox-indeterminate.mat-primary .mat-checkbox-background,
  .mat-checkbox-indeterminate.mat-warn .mat-checkbox-background {
    background-color: transparent;
  }

  .mat-checkbox-checkmark-path {
    stroke: color(foreground) !important;
  }

  .mat-select-panel {
    background: color(dropdown-background);
  }

  .mat-accent,
  .mat-primary,
  .mat-warn {

    .mat-option {
      color: color(dropdown-foreground);

      &.mat-selected:not(.mat-option-disabled) {
        color: color(button-foreground);
      }
    }
  }
}

</style>
</head>
<div class="cc-centered-page">
  <div class="cc-content">
    <div class="title-row stacked">
      <h1>Cloud Code</h1>
      <p>The tools you need for Cloud Native development leveraging your favorite OS, IDE, language and cloud.</p>
    </div>

    <div class="flex-row">

      <div class="flex-50">
        <div class="heading">
          <h2>Version 0.0.9 Release Notes</h2>
        </div>
        <ul class="item">
          <li>Metrics of pods now exposed as a context menu command. (<a href="https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/18">#18</a>)</li>
          <li>Minikube dashboard administrator check has been removed. (<a href="https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/38">#38</a>)</li>
          <li>Cluster creation flow improvements. (<a href="https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/107">#107</a>, <a href="https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/92">#92</a>)</li>
          <li>Various performance improvements. (<a href="https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/83">#83</a>)</li>
        </ul>

        <div class="heading">
          <h2>Key Features</h2>
        </div>
        <ul class="item">
          <li>Support for Go, Node, Java, and Python</li>
          <li>Rapid Edit, Package, Deploy loop to your K8s cluster</li>
          <li>Integrated Debugging and Log Viewing/Streaming</li>
          <li>Snippets, completions, and linting for K8s artifacts</li>
          <li>Profile support for dev, test and production environments</li>
          <li>Cluster management, resource browsing and inspection of K8s clusters</li>
          <li>Cluster creation supporting Amazon EKS, Azure AKS and Google GKE</li>
          <li>Support for Custom Resources (CRDs) e.g. Istio, Knative</li>
        </ul>
      </div>

      <div class="flex-50">
        <div class="heading">
          <h2>Resources</h2>
        </div>
        <!-- TODO(pongad): Link to starter video. -->
        <!-- <div class="item">
          <p class="item-header">Starter Video</p>
          <p>View an overview video showing running through the core features of Cloud Code.</p>
        </div> -->

        <div class="item clickable" (click)="createNewApp()">
          <p class="item-header">Starter Apps</p>
          <p>We have starter applications for Node, Python, Go, C# and Java.</p>
        </div>

        <div class="item clickable" (click)="navTo('https://cloud.google.com/code/docs')">
          <p class="item-header">Review our Docs</p>
          <p>We have a lot of features to explore head over to our documentation to find out.</p>
        </div>

        <div class="item clickable" (click)="newIssue()">
          <p class="item-header">File an Issue</p>
          <p>If you discover an issue please file a bug and we will fix it ASAP.</p>
        </div>

        <div class="item clickable"
          (click)="navTo('https://github.com/GoogleCloudPlatform/cloud-code-vscode/issues/new?labels=enhancement&template=feature_request.md')">
          <p class="item-header">Request a Feature</p>
          <p>If you have any feature requests, ideas for improvement and general feedback please submit a feature
            request.</p>
        </div>

        <div class="heading">
          <h2>Prerequisites</h2>
        </div>
        <p>Please see extension prerequisites <a href="https://cloud.google.com/code/docs/vscode/install">here</a>.</p>
        <p>To help improve Cloud Code plugins, feature usage statistics are collected and sent to Google.
          You can opt-out at any time by visiting <a (click)="openSettings()">Cloud Code Settings</a> and updating
          <code>'Enable Telemetry'</code>
          Your use of this plugin is subject to the <a href='https://policies.google.com/privacy'>Google Privacy
            Policy</a>.</p>
      </div>

    </div>
  </div>
</div>

    """.trimMargin()
        return data
    }

fun main() {
/*
    val frame = JFrame()
    val pane = JEditorPane()
    pane.isEditable = false
    pane.contentType = "text/html"
    val data = htmlPage
    pane.text = data

    frame.add(pane)
    frame.setSize(200, 200)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    frame.isVisible = true
*/
    launch(WebViewExample::class.java)
}


class WebViewExample : Application() {
    override fun start(primaryStageIn: Stage?) {
        val primaryStage = primaryStageIn!!
        primaryStage.setTitle("JavaFX WebView Example")

        val webView = WebView()

        webView.engine.loadContent(htmlPage, "text/html")

        val vBox = VBox(webView)
        val scene = Scene(vBox, 960.0, 600.0)

        primaryStage.setScene(scene)
        primaryStage.show()
    }

}
