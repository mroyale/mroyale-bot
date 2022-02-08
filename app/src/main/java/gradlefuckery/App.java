package gradlefuckery;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Initialization
        String token = getToken();
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        api.updateActivity("Mario Royale | No DMs yet!");
        String VERSION = "Release 1.1";

        // Message listener event
        api.addMessageCreateListener(event -> {
            String message = event.getMessageContent();
            String[] arguments = message.split(" ");
            String msgContent = arguments[0].toLowerCase(Locale.ROOT);
            String author = event.getMessageAuthor().getIdAsString();

            if (author != "669944860874113028") {
                switch (msgContent) {
                    case ".link" -> {
                        EmbedBuilder embed = new EmbedBuilder()
                                .setTitle("Here's the Mario Royale link!")
                                .setDescription("Click [here](https://marioroyale.tk/) to play Mario Royale!")
                                .setColor(Color.RED)
                                .setFooter("Mario Royale Bot")
                                .setThumbnail("https://cdn.discordapp.com/attachments/504028063948865536/645702802147901454/e7fab1e75ccfeb1484df5f1ec76c6796.gif");

                        event.getChannel().sendMessage(embed);
                    }

                    case ".info" -> {
                        EmbedBuilder infoEmbed = new EmbedBuilder()
                                .setTitle("Here's some information about me:")
                                .addInlineField("Author:", "terminalarch#8246")
                                .addInlineField("Version:", VERSION)
                                .addInlineField("Invite:", "[Royale Bot](" + api.createBotInvite() + ")")
                                .setColor(Color.RED)
                                .setFooter("Mario Royale Bot");

                        event.getChannel().sendMessage(infoEmbed);
                    }

                    /* == FUN == */
                    case ".joke" -> {
                        String[] jokeList = {"How do you organize a space party?\n||You planet||", "What sound does a sleeping T-Rex make?\n||A dino-snore.||", "What kind of shorts do clouds wear?\n||Thunderwear||", "Why did the banana go to the doctor?\n||It wasn't peeling well||", "What do you call an everyday potato?\n||A commentator||", "If you were a fruit, you'd be a fine-apple", "What do you call a pig that does karate?\n||Pork chop||", "What do you call a bee that can't make up its mind?\n||A Maybe||", "What do you call a thieving alligator? A Crookodile", "All these sea monster jokes are just Kraken me up.", "I was going to make myself a belt made out of watches, but then I realized it would be a waist of time.", "The machine at the coin factory just suddenly stopped working, with no explanation. It doesn't make any cents!", "Yesterday, a clown held the door open for me. It was such a nice jester!", "I asked my French friend if she likes to play video games. She said, \"Wii.\"", "I'm a big fan of whiteboards. I find them quite re-markable.", "Don't interrupt someone working intently on a puzzle. Chances are, you'll hear some crosswords.", "Today, my son asked \"Can I have a book mark?\" and I burst into tears. 11 years old and he still doesn't know my name is Brian.", "My wife is really mad at the fact that I have no sense of direction. So I packed up my stuff and right.", "DAD: I was just listening to the radio on my way in to town, apparently an actress just killed herself.\nMOM: Oh my! Who!?\nDAD: Uh, I can't remember... I think her name was Reese something?\nDAD: No, it was with a knife...", "How do you make holy water? You boil the hell out of it.", "I bought some shoes from a drug dealer. I don't know what he laced them with, but I was tripping all day!", "If a child refuses to sleep during nap time, are they guilty of resisting a rest?", "The secret service isn't allowed to yell \"Get down!\" anymore when the president is about to be attacked. Now they have to yell \"Donald, duck!\"",
                                "I'm reading a book about anti-gravity. It's impossible to put down!", "What do you call someone with no body and no nose? Nobody knows.", "I ordered a chicken and an egg from Amazon. I'll let you know", "What is the least spoken language in the world? Sign language", "A slice of apple pie is $2.50 in Jamaica and $3.00 in the Bahamas. These are the pie rates of the Caribbean.", "My wife tried to unlatch our daughter's car seat with one hand and said, \"How do one armed mothers do it?\" Without missing a beat I replied, \"Single handedly.\"", "When a dad drives past a graveyard: Did you know that's a popular cemetery? Yep, people are just dying to get in there!", "My friend keeps saying \"Cheer up man it could be worse, you could be stuck underground in a hole full of water.\" I know he means well.", "Justice is a dish best served cold, if it were served warm it would be justwater.", "The fattest knight at King Arthur's round table was Sir Cumference. He acquired his size from too much pi.", "MOM: \"How do I look?\" DAD: \"With your eyes.\"", "Why can't you hear a pterodactyl go to the bathroom? Because the pee is silent.", "What does a zombie vegetarian eat? \"GRRRAAAAAIIIINNNNS!\"", "Spring is here! I got so excited I wet my plants!", "3 unwritten rules of life...\n1.\n2.\n3.", "If you see a robbery at an Apple Store does that make you an iWitness?", "Did you hear the news? FedEx and UPS are merging. They're going to go by the name Fed-Up from now on.", "Don't trust atoms. They make up everything!", "I told my son I was named after Thomas Jefferson... He said, \"But dad, your name is Brian.\" I said, \"I know, but I was named AFTER Thomas Jefferson.\"", "KID: \"Dad, make me a sandwich!\" DAD: \"Poof, you're a sandwich!\"", "Why did the invisible man turn down the job offer? He couldn't see himself doing it.", "SERVER: \"Sorry about your wait.\" DAD: \"Are you saying I'm fat?\"", "What has two butts and kills people? An assassin", "What did the pirate say on his 80th birthday? ||AYE MATEY||"};

                        Random random = new Random();
                        int choice = random.nextInt(jokeList.length);

                        event.getChannel().sendMessage(jokeList[choice]);
                    }

                    case ".8ball" -> {
                        String[] responseList = {"most certainly", "maybe", "ask again", "try again later", "definitely not",
                                "get a shovel and bury that question", "yes", "no", "throw a dice instead", "with some luck"};

                        try {
                            Random response = new Random();
                            int choice = response.nextInt(responseList.length);

                            event.getChannel().sendMessage(responseList[choice]);
                        } catch (ArrayIndexOutOfBoundsException e) { return; }
                    }

                    case ".choice" -> {
                        try {
                            String[] formatList = message.split(" ", 2);
                            String[] choiceList = formatList[1].split(",");


                            if (choiceList.length < 21) {
                                int length = choiceList.length - 1;
                                int choice = randint(0, length);

                                if (isBlacklisted(choiceList[choice])) { event.getChannel().sendMessage("No."); return; }
                                event.getChannel().sendMessage("<@" + author + ">, I choose: " + choiceList[choice]);
                            } else { event.getChannel().sendMessage("<@" + author + "> Too many choices!"); }
                        } catch (ArrayIndexOutOfBoundsException e) { return; }
                    }

                    case ".fight" -> {
                        try {
                            String winnerName = "";
                            String loserName = "";
                            String[] fightArgs = message.split(" ", 2);
                            String fighter = fightArgs[1];
                            int winner = randint(1, 2);

                            if (isBlacklisted(fighter)) { event.getChannel().sendMessage("No."); return; }

                            if (winner == 1) {
                                winnerName = "<@" + author + ">";
                                loserName = fighter;
                            } else if (winner == 2) {
                                winnerName = fighter;
                                loserName = "<@" + author + ">";
                            }

                            String[] choices = {winnerName + " pushes " + loserName + " off a cliff.", winnerName + " turns " + loserName + " into a magic Mushroom Kingdom block",
                                    winnerName + " turns into a smeagol and suffocates " + loserName, winnerName + " jumps on " + loserName + "'s head."};

                            int length = choices.length - 1;
                            int choice = randint(0, length);
                            event.getChannel().sendMessage(choices[choice]);
                        } catch (ArrayIndexOutOfBoundsException e) { return; }
                    }

                    case ".hg" -> {
                        try {
                            String[] formatList = message.split(" ", 2);
                            String[] characters = formatList[1].trim().split(",");

                            for (String elem : characters) {
                                if (elem.length() < 3) {
                                    event.getChannel().sendMessage("Please choose longer names for your characters.");
                                    return;
                                }
                            }

                            // single character
                            if (characters.length < 2) {
                                if (isBlacklisted(characters[0])) { event.getChannel().sendMessage("No."); return; } // it should detect this right off the bat
                                event.getChannel().sendMessage("As a single person, " + characters[0] + " wins by default.");
                            } else {
                                if (characters.length < 21) {
                                    // setup
                                    List<String> survivors = new ArrayList<String>();
                                    List<String> finalSurvivors = new ArrayList<String>();

                                    String[] actions1 = {"was caught venting and subsequently ejected", "was grabbed by luigi at 0%", "dies after stumbling over a branch", "didn't clear his fucking cache. He suffered a laggy despawn from life", "hears someone shout \"So long gay Bowser!\" and then gets squished by a giant tortoise", "gets slowly digested over 1000 years, by a Piranha Plant", "commits suicide", "drowns", "finds a power star and dies due to an overdose of it","gets their head cut off", "finds some rocks to make a tool", "creates a fire", "finds firecrackers", "makes a bow out of a stick and a rope they found", "makes a makeshift knife out of the bones of their enemy", "does some parkour", "watches the sun rise", "laughs at gay luigi", "builds a shelter", "finds berries", "befriends a wild pig", "befriends a wild Koopa"};
                                    String[] actions2 = {"was caught venting and subsequently ejected", "fell off a cliff and broke every bone in their body", "gets killed by a mysterious stranger", "didn't clear his fucking cache. He suffered a laggy despawn from life", "steps into a lethal trap", "gets burned with a fire flower", "got to the bridge too late and fell into the lava with bowser", "finds the magic mushroom kingdom and stays", "gets stomped on by a Mario immitator", "hides in a cave they found", "gets saved from a mysterious stranger by gay Luigi", "hides in a tree until everything's over", "wanders around without finding anyone", "found some pipeweed and has a good time"};
                                    int maxActions1 = actions1.length - 1;
                                    int maxActions2 = actions2.length - 1;

                                    StringBuilder firstMSG = new StringBuilder();
                                    firstMSG.append("**The preparation phase starts.**\n");

                                    StringBuilder secondMSG = new StringBuilder();
                                    secondMSG.append("**The preperation phase is over and the fights begin.**\n");

                                    StringBuilder thirdMSG = new StringBuilder();
                                    thirdMSG.append("**Time is up.** ");

                                    boolean skip = false;

                                    // PHASE ONE
                                    for (String elem: characters) {
                                        int choice = randint(0, maxActions1);
                                        if (isBlacklisted(elem)) { event.getChannel().sendMessage("No."); return; } // it should detect this right off the bat

                                        firstMSG.append("> " + elem + " " + actions1[choice] + "\n");
                                        if (choice > 9) { survivors.add(elem); }
                                    }

                                    // PHASE TWO
                                    if (survivors.size() > 1) {
                                        // proceed as normal
                                        for (String elem: survivors) {
                                            int choice = randint(0, maxActions2);
                                            secondMSG.append("> " + elem + " " + actions2[choice] + "\n");
                                            if (choice > 8) {
                                                finalSurvivors.add(elem);
                                            }
                                        }
                                    } else if (survivors.size() == 1) {
                                        secondMSG.delete(0, secondMSG.length());
                                        secondMSG.append("**" + survivors.get(0) + " looks around and sees that they're the only left, making them the winner.**");
                                        thirdMSG.setLength(0);
                                        skip = true;
                                    } else {
                                        secondMSG.delete(0, secondMSG.length());
                                        secondMSG.append("**The wind howls through the empty mushroom kingdom, as everyone is dead**");
                                        thirdMSG.setLength(0);
                                        skip = true;
                                    }

                                    // PHASE THREE
                                    if (skip == false) {
                                        if (finalSurvivors.size() > 0) {
                                            if (finalSurvivors.size() == 1) { thirdMSG.append("The winner is: __**" + finalSurvivors.get(0) + "**__"); }
                                            else {
                                                StringBuilder winners = new StringBuilder();
                                                int finalCharNum = finalSurvivors.size() - 1;
                                                String finalChar = finalSurvivors.get(finalCharNum);
                                                finalSurvivors.remove(finalChar);

                                                for (String elem : finalSurvivors) {
                                                    winners.append(elem + ", ");
                                                }

                                                winners.append(finalChar);
                                                thirdMSG.append("The winners are: __**" + winners + "**__");
                                            }
                                        } else {
                                            thirdMSG.append("Everyone is dead and there are no winners.");
                                        }
                                    }

                                    // END
                                    StringBuilder finalMessage = new StringBuilder();
                                    finalMessage.append(firstMSG);
                                    finalMessage.append(secondMSG);
                                    finalMessage.append(thirdMSG);

                                    event.getChannel().sendMessage(finalMessage.toString());
                                } else { event.getChannel().sendMessage("Too many characters!"); }
                            }
                        } catch (ArrayIndexOutOfBoundsException e) { return; }
                    }

                    /* == RULES == */
                    case ".rule" -> {
                        // get rules
                        HashMap<Integer, String> ruleList = getRules();

                        // send rule
                        try {
                            String rule = ruleList.get(Integer.parseInt(arguments[1])); // java moment

                            EmbedBuilder ruleEmbed = new EmbedBuilder()
                                    .setTitle("**Rule " + arguments[1] + "**")
                                    .setDescription(rule + "\n*[To see all rules, click here](https://discord.com/channels/831370685980475394/834877839697772584/834890818597814282)*")
                                    .setFooter("Disobeying a rule will result in staff taking action")
                                    .setColor(Color.RED);

                            event.getChannel().sendMessage(ruleEmbed);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            event.getChannel().sendMessage("Invalid rule.");
                        }
                    }

                    /* == HELP == */
                    case ".help" -> {
                        EmbedBuilder defaultEmbed = new EmbedBuilder()
                                .setTitle("**Help**")
                                .setDescription("Please specify what you would like to have help with. You can choose one of the three different topics:")
                                .addField(".help general", "Will provide help for general commands")
                                .addField(".help fun", "Will provide help with the fun commands")
                                .setFooter("Mario Royale Server")
                                .setColor(Color.RED)
                                .setThumbnail("https://cdn.discordapp.com/attachments/504028063948865536/645702802147901454/e7fab1e75ccfeb1484df5f1ec76c6796.gif");

                        EmbedBuilder generalEmbed = new EmbedBuilder()
                                .setTitle("General Commands Help")
                                .setDescription("Here are some general commands that anyone can use")
                                .addField("**.link**", "Gives the link to Mario Royale")
                                .addField("**.info**", "Gives basic information about the bot")
                                .addField("**.rule [rule]**", "Gives content of a rule, assuming it exists.")
                                .setFooter("Mario Royale Server")
                                .setColor(Color.RED)
                                .setThumbnail("https://cdn.discordapp.com/attachments/504028063948865536/645702802147901454/e7fab1e75ccfeb1484df5f1ec76c6796.gif");

                        EmbedBuilder funEmbed = new EmbedBuilder()
                                .setTitle("Fun Commands Help")
                                .setDescription("Here are some commands that are solely for entertainment and have no further use")
                                .addField("**.joke**", "Tells a random joke")
                                .addField("**.8ball**", "Ask the magic eight ball a question..")
                                .addField("**.choice [value 1], [value 2], etc..**", "Chooses from a list seperated by commas.")
                                .addField("**.fight [value]**", "Fight somebody.")
                                .addField("**.hg**", "The hunger games. You can use a max of 20 characters.")
                                .setFooter("Mario Royale Server")
                                .setColor(Color.RED)
                                .setThumbnail("https://cdn.discordapp.com/attachments/504028063948865536/645702802147901454/e7fab1e75ccfeb1484df5f1ec76c6796.gif");

                        try {
                            String arg = arguments[1];

                            if (Objects.equals(arg, "general")) { event.getChannel().sendMessage(generalEmbed); }
                            if (Objects.equals(arg, "fun")) { event.getChannel().sendMessage(funEmbed); }
                        } catch (ArrayIndexOutOfBoundsException e) { event.getChannel().sendMessage(defaultEmbed); }
                    }
                }
            }
        });

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

    /* Check if word is blacklisted */
    public static boolean isBlacklisted(String query) {
        String[] blacklisted = {"@everyone", "@here"};
        boolean isTrue = Arrays.stream(blacklisted).anyMatch(query::contains);

        return isTrue; // at this point return the bool
    }

    /* Python3 randint replacement */
    public static int randint(int a, int b) {
        return (int) ((Math.random() * (b - a)) + a);
    }

    /* Get rules (this to avoid clutter in main function) */
    public static HashMap getRules() {
        HashMap<Integer, String> ruleList = new HashMap<Integer, String>();

        // add rules
        ruleList.put(1, "Don't be a troll/jerk/smartass or act retarded. This comes mostly in the form of humor. What you deem funny, others may not find it to be so.");
        ruleList.put(2, "Avoid causing drama and don't be racist.");
        ruleList.put(3, "Don't question the action of a Staff member. They know what's best for the server.");
        ruleList.put(4, "Don't make gender/sexist/gay jokes/insults.");
        ruleList.put(5, "Don't post gory/pornographic/NSFW content/ASCII posts");
        ruleList.put(6, "Don't discuss politics or religion.");
        ruleList.put(7, "Don't spam.");
        ruleList.put(8, "Don't promote your own content unless relevant");
        ruleList.put(9, "Don't ask for moderator/administrator roles.");
        ruleList.put(10, "Keep memes/shitpost in <#834890818597814282>. Memes are allowed in other channels if relevant");
        ruleList.put(11, "We will enforce the underage rule from the discord ToS. If you are found out to be under the age of 13, you will be removed. https://discordapp.com/terms");
        ruleList.put(12, "Anything the staff deem as \"stupidity\" is grounds for a ban at the discretion of the staff. e.g. posting paragraphs of incomprehensible English, sending skins in <#834877847268229120> that don't follow any bit of the templates (generally images downloaded directly from spriter's resources), etc.");

        // return hashmap
        return ruleList;
    }

    /* Get token from file */
    public static String getToken() {
        try {
            File token = new File("token.txt"); // Token should be placed at the root (/app) of the project
            Scanner scan = new Scanner(token);

            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                return data;
            }

            scan.close(); // reading should be closed now
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "An error occurred.";
        }

        return ""; // "Good faith, I guess.."
    }

}
