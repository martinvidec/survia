package at.videc.survia.node.service.api;

import at.videc.survia.node.domain.model.User;

public interface IUserService {
    User createAdminUser(String username);

    User createDefaultUser(String username);

    User getUser(String username);
}
