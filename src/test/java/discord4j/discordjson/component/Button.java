/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.discordjson.component;

import discord4j.discordjson.json.ComponentData;
import discord4j.discordjson.json.ImmutableComponentData;
import reactor.util.annotation.Nullable;

import java.util.Optional;

/**
 * A message button.
 *
 * @see <a href="https://discord.com/developers/docs/interactions/message-components#buttons">Buttons</a>
 */
public class Button extends ActionComponent {

    /**
     * Creates a {@link Style#PRIMARY primary} button.
     *
     * @param customId A developer-defined identifier for the button.
     * @param label The text that appears on the button.
     * @return A button with the given data.
     */
    public static Button primary(String customId, String label) {
        return of(Style.PRIMARY, customId, label, null);
    }

    /**
     * Creates a {@link Style#SECONDARY secondary} button.
     *
     * @param customId A developer-defined identifier for the button.
     * @param label The text that appears on the button.
     * @return A button with the given data.
     */
    public static Button secondary(String customId, String label) {
        return of(Style.SECONDARY, customId, label, null);
    }

    /**
     * Creates a {@link Style#SUCCESS success} button.
     *
     * @param customId A developer-defined identifier for the button.
     * @param label The text that appears on the button.
     * @return A button with the given data.
     */
    public static Button success(String customId, String label) {
        return of(Style.SUCCESS, customId, label, null);
    }

    /**
     * Creates a {@link Style#DANGER danger} button.
     *
     * @param customId A developer-defined identifier for the button.
     * @param label The text that appears on the button.
     * @return A button with the given data.
     */
    public static Button danger(String customId, String label) {
        return of(Style.DANGER, customId, label, null);
    }

    /**
     * Creates a {@link Style#LINK link} button.
     *
     * @param url The url to navigate to when clicked.
     * @param label The text that appears on the button.
     * @return A button with the given data.
     */
    public static Button link(String url, String label) {
        return of(Style.LINK, null, label, url);
    }

    private static Button of(Style style, @Nullable String customId, @Nullable String label, @Nullable String url) {
        ImmutableComponentData.Builder builder = ComponentData.builder()
                .type(MessageComponent.Type.BUTTON.getValue())
                .style(style.getValue());

        if (customId != null)
            builder.customId(customId);

        if (label != null)
            builder.label(label);

        if (url != null)
            builder.url(url);

        return new Button(builder.build());
    }

    Button(ComponentData data) {
        super(data);
    }

    /**
     * Gets the button's style.
     *
     * @return The button's style.
     */
    public Style getStyle() {
        return getData().style().toOptional()
                .map(Style::of)
                .orElseThrow(IllegalStateException::new); // style should always be present on buttons
    }

    /**
     * Gets button's label.
     *
     * @return The button's label.
     */
    public Optional<String> getLabel() {
        return getData().label().toOptional();
    }

    /**
     * Gets the button's custom id.
     *
     * @return The button's custom id.
     */
    public Optional<String> getCustomId() {
        return getData().customId().toOptional();
    }

    /**
     * Gets the button's url.
     *
     * @return The button's url.
     */
    public Optional<String> getUrl() {
        return getData().url().toOptional();
    }

    /**
     * Gets whether button is disabled.
     *
     * @return Whether the button is disabled.
     */
    public boolean isDisabled() {
        return getData().disabled().toOptional().orElse(false);
    }

    /**
     * Creates a new button with the same data as this one, but disabled.
     *
     * @return A new disabled button with the same data as this one.
     */
    public Button disabled() {
        return disabled(true);
    }

    /**
     * Creates a new button with the same data as this one, but depending on the value param it may be disabled or not.
     *
     * @param value True if the button should be disabled otherwise False.
     * @return A new possibly disabled button with the same data as this one.
     */
    public Button disabled(boolean value) {
        return new Button(ComponentData.builder().from(getData()).disabled(value).build());
    }

    /**
     * A button's style is what determines its color and whether it has a custom id or a url.
     *
     * @see <a href="https://discord.com/developers/docs/interactions/message-components#buttons-button-styles">Button Styles</a>
     */
    public enum Style {
        UNKNOWN(-1),
        PRIMARY(1),
        SECONDARY(2),
        SUCCESS(3),
        DANGER(4),
        LINK(5);

        private final int value;

        Style(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Style of(int value) {
            switch (value) {
                case 1: return PRIMARY;
                case 2: return SECONDARY;
                case 3: return SUCCESS;
                case 4: return DANGER;
                case 5: return LINK;
                default: return UNKNOWN;
            }
        }
    }
}
