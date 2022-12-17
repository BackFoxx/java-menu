package menu.controller;

import menu.controller.system.AbstractController;
import menu.domain.Coach;
import menu.domain.CoachRepository;
import menu.domain.Menu;
import menu.domain.MenuRepository;
import menu.inputview.CoachInfoInputView;
import menu.outputview.CoachInfoOutputView;

import java.util.List;

public class CoachInfoController extends AbstractController {
    @Override
    public void doProcess() {
        List<Coach> coaches = getCoaches();
        CoachRepository.saveAll(coaches);

        saveBannedMenus(coaches);
    }

    private static void saveBannedMenus(List<Coach> coaches) {
        for (Coach coach : coaches) {
            CoachInfoOutputView.printAskingMenuBanned(coach.getName());
            List<Menu> bannedMenus = CoachInfoInputView.getMenusBanned();
            MenuRepository.saveBannedMenu(coach, bannedMenus);
        }
    }

    private static List<Coach> getCoaches() {
        CoachInfoOutputView.printAskingCoachNames();
        return CoachInfoInputView.getCoaches();
    }
}
