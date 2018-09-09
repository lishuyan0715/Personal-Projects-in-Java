import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {
	
	/**
	 * Print possible commands
	 */
	public static void commands() {
		System.out.println("Valid commands: ");
		System.out.printf("\t mine: discover the nonce for a given transaction\n");
		System.out.printf("\t append: appends a new block onto the end of the chain\n");
		System.out.printf("\t remove: removes the last block from the end of the chain\n");
		System.out.printf("\t check: checks that the block chain is valid\n");
		System.out.printf("\t report: reports the balances of Alice and Bob\n");
		System.out.printf("\t help: prints this list of commands\n");
		System.out.printf("\t quit: quits the program\n");
	}


	public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
		BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
		Scanner in = new Scanner(System.in);
		Block mined = chain.first.block;
		boolean quit = false;
		while(!quit) {
			System.out.printf("\n" + chain.toString());
			System.out.print("Command? ");
			String cmd = in.nextLine();
			switch(cmd){	
			case "mine":
				System.out.print("Amount transferred? ");
				int tranamount = in.nextInt();
				mined = chain.mine(tranamount);
				System.out.printf("amount = %d, nonce = %d\n", mined.getAmount(), mined.getNonce());
				in.nextLine(); //prevent issuance of invalid command
				break;
			case "append":
				System.out.print("Amount transfered? ");
				int amount = in.nextInt();
				System.out.print("Nonce? ");
				long nonce = in.nextLong();
				if(mined.getNonce() == nonce && mined.getAmount() == amount) {
					chain.append(mined);
				}
				else {
					System.out.println("Nonce or amount does not match previously mined block");
				}
				in.nextLine(); //prevent issuance of invalid command
				break;
			case "remove":
				chain.removeLast();
				break;
			case "check":
				if(chain.isValidBlockChain()) {
					System.out.println("Chain is valid!");
				}
				else {
					System.out.println("Chain is invalid!");
				}
				break;
			case "report":
				chain.printBalance();
				break;
			case "help":
				commands();
				break;
			case "quit":
				quit = true;
				break;
			default:
				System.out.println("Invalid Command");
				break;
			}//switch
		}//while
		in.close();
	} //main
}
