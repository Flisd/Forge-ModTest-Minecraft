package net.agent.testmod.command.custom;

import net.agent.testmod.item.ModItems;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Mod.EventBusSubscriber
public class ChatGameCommand {
    private static final QuestionBank questionBank;

    static {
        try {
            questionBank = new QuestionBank();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Map<String, List<ItemStack>> rewardItems = new HashMap<>();
    private static final Random random = new Random();
    private static Question currentQuestion;
    private static ServerPlayer currentPlayer;
    private static Timer timer;

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("chatGame")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    String[] diff = new String[]{"easy", "medium", "hard", "super hard"};
                    startChatGame(player, diff[random.nextInt(diff.length)]);
                    return 1;
                }));
    }

    private static void initializeRewards() {
        rewardItems.put("easy", Arrays.asList(new ItemStack(Items.APPLE, 10), new ItemStack(Items.BREAD, 32)));
        rewardItems.put("medium", Arrays.asList(new ItemStack(Items.IRON_SWORD), new ItemStack(Items.BOW)));
        rewardItems.put("hard", Arrays.asList(new ItemStack(Items.DIAMOND, 20), new ItemStack(ModItems.STRAWBERRY.get(), 32)));
        rewardItems.put("super hard", Arrays.asList(new ItemStack(Items.NETHERITE_INGOT, 2), new ItemStack(Items.TOTEM_OF_UNDYING, 5), new ItemStack(ModItems.TOTEM_OF_TECHNO.get(), 2), new ItemStack(ModItems.GOD_ORB.get(), 4)));
    }

    private static void startChatGame(ServerPlayer player, String difficulty) {
        initializeRewards();

        List<Question> questions = questionBank.getQuestions(difficulty);
        currentQuestion = questions.get(random.nextInt(questions.size()));
        currentPlayer = player;
        player.sendSystemMessage(Component.literal("Question: " + currentQuestion.getQuestion()));

        int timeLimit = getTimeLimit(difficulty);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.sendSystemMessage(Component.literal("Time's up!"));
                currentQuestion = null;
                currentPlayer = null;
            }
        }, timeLimit * 1000L);
    }

    private static int getTimeLimit(String difficulty) {
        return switch (difficulty) {
            case "easy" -> 10;
            case "medium" -> 20;
            case "hard" -> 30;
            case "super hard" -> 40;
            default -> 50;
        };
    }

    private static void rewardPlayer(ServerPlayer player, String difficulty) {
        List<ItemStack> items = rewardItems.get(difficulty);
        ItemStack reward = items.get(random.nextInt(items.size()));
        player.getInventory().add(reward);
        player.sendSystemMessage(Component.literal("You have been rewarded with: " + reward.getHoverName().getString()));
    }

    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        if (currentQuestion != null && currentPlayer != null && event.getPlayer().equals(currentPlayer)) {
            String playerAnswer = String.valueOf(event.getMessage());
            if (playerAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
                currentPlayer.sendSystemMessage(Component.literal("Correct!"));
                rewardPlayer(currentPlayer, currentQuestion.getDifficulty());
                currentQuestion = null;
                currentPlayer = null;
                timer.cancel();
            } else {
                currentPlayer.sendSystemMessage(Component.literal("Incorrect! Try again."));
            }
        }
    }

    private static class QuestionBank {
        private final Map<String, List<Question>> questionsByDifficulty = new HashMap<>();

        public QuestionBank() throws FileNotFoundException {
            readFileAddQuestions();
        }

        public void addQuestion(String difficulty, String question, String answer) {
            questionsByDifficulty.computeIfAbsent(difficulty, k -> new ArrayList<>()).add(new Question(difficulty, question, answer));
        }

        public List<Question> getQuestions(String difficulty) {
            return questionsByDifficulty.getOrDefault(difficulty, Collections.emptyList());
        }

        public void readFileAddQuestions() throws FileNotFoundException {
            String fileName = "QuestionFile.txt";
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] brokenList = line.split("~");
                String diff = switch (brokenList[0].toLowerCase()) {
                    case "e" -> "easy";
                    case "m" -> "medium";
                    case "h" -> "hard";
                    default -> "super hard";
                };
                String question = brokenList[1];
                String answer = brokenList[2];
                addQuestion(diff, question, answer);
            }
            scan.close();
        }
    }

    private static class Question {
        private final String difficulty;
        private final String question;
        private final String answer;

        public Question(String difficulty, String question, String answer) {
            this.difficulty = difficulty;
            this.question = question;
            this.answer = answer;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }
    }
}
