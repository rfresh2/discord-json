package discord4j.discordjson.encoding.possible;

import discord4j.discordjson.Id;
import discord4j.discordjson.possible.Possible;
import org.immutables.encode.Encoding;
import javax.annotation.Nullable;

import java.util.Objects;
import java.util.Optional;

@Encoding
public class PossibleOptionalIdEncoding {

    @Encoding.Impl(virtual = true)
    private Possible<Optional<Id>> possible = discord4j.discordjson.possible.Possible.absent();

    private final long value = discord4j.discordjson.possible.Possible.flatOpt(possible)
            .map(discord4j.discordjson.Id::asLong)
            .orElse(0L);
    private final boolean absent = possible.isAbsent();
    private final boolean isNull = !possible.isAbsent() && !possible.get().isPresent();

    @Encoding.Expose
    Possible<Optional<Id>> get() {
        if (absent) {
            return discord4j.discordjson.possible.Possible.absent();
        } else if (isNull) {
            return discord4j.discordjson.possible.Possible.of(Optional.empty());
        } else {
            return discord4j.discordjson.possible.Possible.of(Optional.of(discord4j.discordjson.Id.of(value)));
        }
    }

    @Encoding.Naming("is*Present")
    boolean isPresent() {
        return !absent;
    }

    @Encoding.Naming("*OrElse")
    long orElse(long defaultValue) {
        return !absent ? value : defaultValue;
    }

    @Encoding.Copy
    public Possible<Optional<Id>> withPossible(final Possible<Optional<Id>> value) {
        return Objects.requireNonNull(value);
    }

    @Encoding.Copy
    @Deprecated
    public Possible<Optional<Id>> with(@Nullable Long value) {
        return value == null ? discord4j.discordjson.possible.Possible.of(Optional.empty()) :
                discord4j.discordjson.possible.Possible.of(Optional.of(discord4j.discordjson.Id.of(value)));
    }

    @Encoding.Naming("with*OrNull")
    @Encoding.Copy
    public Possible<Optional<Id>> withOrNull(@Nullable Long value) {
        return value == null ? discord4j.discordjson.possible.Possible.of(Optional.empty()) :
                discord4j.discordjson.possible.Possible.of(Optional.of(discord4j.discordjson.Id.of(value)));
    }

    @Encoding.Builder
    static class Builder {

        private Possible<Optional<Id>> possible = discord4j.discordjson.possible.Possible.absent();

        @Encoding.Init
        public void setStringValue(String value) {
            this.possible = discord4j.discordjson.possible.Possible.of(Optional.of(discord4j.discordjson.Id.of(value)));
        }

        @Encoding.Init
        public void setLongValue(long value) {
            this.possible = discord4j.discordjson.possible.Possible.of(Optional.of(discord4j.discordjson.Id.of(value)));
        }

        @Encoding.Init
        public void setIdValue(Id value) {
            this.possible = discord4j.discordjson.possible.Possible.of(Optional.of(value));
        }

        @Encoding.Init
        @Encoding.Copy
        public void copyPossibleId(Possible<Optional<Id>> value) {
            this.possible = value;
        }

        @Encoding.Build
        Possible<Optional<Id>> build() {
            return this.possible;
        }
    }
}
