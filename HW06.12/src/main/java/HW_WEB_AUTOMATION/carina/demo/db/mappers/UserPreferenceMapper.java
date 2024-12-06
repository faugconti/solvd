package HW_WEB_AUTOMATION.carina.demo.db.mappers;

import HW_WEB_AUTOMATION.carina.demo.db.models.UserPreference;

public interface UserPreferenceMapper {

	void create(UserPreference userPreference);

	UserPreference findById(Long id);
}
