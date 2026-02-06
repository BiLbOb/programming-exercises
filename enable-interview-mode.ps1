# VS Code Interview Mode - Disable AI Assistance
# Run this script before your interview

$settingsPath = "$env:APPDATA\Code\User\settings.json"
$backupPath = "$env:APPDATA\Code\User\settings.backup.json"

Write-Host "Enabling VS Code Interview Mode..." -ForegroundColor Yellow

# Backup current settings
if (Test-Path $settingsPath) {
    Copy-Item $settingsPath $backupPath -Force
    Write-Host "Settings backed up" -ForegroundColor Green
} else {
    Write-Host "No existing settings found" -ForegroundColor Yellow
}

# Create interview settings
$interviewSettings = @'
{
    "github.copilot.enable": {
        "*": false
    },
    "github.copilot.inlineSuggest.enable": false,
    "github.copilot.editor.enableAutoCompletions": false,
    "editor.quickSuggestions": {
        "comments": "off",
        "strings": "off",
        "other": "off"
    },
    "editor.suggestOnTriggerCharacters": false,
    "editor.acceptSuggestionOnEnter": "off",
    "editor.inlayHints.enabled": "never",
    "editor.wordBasedSuggestions": "off",
    "editor.inlineSuggest.enabled": false,
    "editor.suggest.showWords": false,
    "amazonQ.codeWhisperer.shareCodeWhispererContentWithAWS": false,
    "amazonQ.suppressPrompts": true,
    "aws.codeWhisperer.includeSuggestionsWithCodeReferences": false,
    "github.copilot.chat.agent.currentEditorContext.enabled": false,
    "github.copilot.chat.anthropic.toolSearchTool.enabled": false,
    "github.copilot.chat.claudeAgent.enabled": false
}
'@

# Apply settings
Set-Content -Path $settingsPath -Value $interviewSettings
Write-Host "Interview mode enabled" -ForegroundColor Green
Write-Host "" 
Write-Host "Disabled:" -ForegroundColor Cyan
Write-Host "  - GitHub Copilot (all features)" -ForegroundColor White
Write-Host "  - Amazon Q inline suggestions" -ForegroundColor White
Write-Host "  - Editor autocomplete & suggestions" -ForegroundColor White
Write-Host "  - Inline hints" -ForegroundColor White
Write-Host "" 
Write-Host "IMPORTANT: Restart VS Code now" -ForegroundColor Yellow
Write-Host "Run 'restore-vscode-settings.ps1' after interview" -ForegroundColor Yellow