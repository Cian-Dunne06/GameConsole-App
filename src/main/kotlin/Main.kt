import service.ConsoleService
import service.GameService
import ui.MainMenu

fun main() {
    MainMenu(GameService(), ConsoleService()).start()
}