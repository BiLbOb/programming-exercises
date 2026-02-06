# VS Code Interview Mode - Restore Original Settings
# Run this script after your interview

$settingsPath = "$env:APPDATA\Code\User\settings.json"
$backupPath = "$env:APPDATA\Code\User\settings.backup.json"

Write-Host "Restoring VS Code settings..." -ForegroundColor Yellow
Write-Host "" 

if (Test-Path $backupPath) {
    Copy-Item $backupPath $settingsPath -Force
    Remove-Item $backupPath -Force
    Write-Host "Settings restored successfully" -ForegroundColor Green
    Write-Host "" 
    Write-Host "Restored:" -ForegroundColor Cyan
    Write-Host "  - All AI assistance features" -ForegroundColor White
    Write-Host "  - Editor suggestions & autocomplete" -ForegroundColor White
    Write-Host "  - Original configuration" -ForegroundColor White
    Write-Host "" 
    Write-Host "IMPORTANT: Restart VS Code now" -ForegroundColor Yellow
} else {
    Write-Host "No backup found" -ForegroundColor Red
    Write-Host "Cannot restore settings" -ForegroundColor Red
}