package discord4j.discordjson.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import discord4j.discordjson.possible.Possible;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutablePartialUserData.class)
@JsonDeserialize(as = ImmutablePartialUserData.class)
public interface PartialUserData {

    String id();

    Possible<String> username();

    Possible<String> discriminator();

    Possible<Optional<String>> avatar();

    Possible<Boolean> bot();

    Possible<Boolean> system();

    @JsonProperty("mfa_enabled")
    Possible<Boolean> mfaEnabled();

    Possible<String> locale();

    Possible<Boolean> verified();

    Possible<String> email();

    Possible<Integer> flags();

    @JsonProperty("premium_type")
    Possible<Integer> premiumType();

    @JsonProperty("public_flags")
    Possible<Integer> publicFlags();
}
