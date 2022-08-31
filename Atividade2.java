package atv2;

public class Atividade2 implements Runnable {
		public void run() {

			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "  " + i);
				try {
					Thread.sleep(1000);
				}

				catch (Exception e) {
					System.out.println(e);
				}
			}
			Thread t = Thread.currentThread();
			System.out.println("Current thread: " + t.getName());
			System.out.println("Is Alive? " + t.isAlive());

		}

		public static void main(String[] args) throws Exception {
			Thread t1 = new Thread(new Atividade2());
			t1.start();
			
			Thread t2 = new Thread(new Atividade2());
	        t2.start();

			t1.join(5000);
			System.out.println("\nJoining after 5000" + " milliseconds: \n");
			System.out.println("Current thread: " + t1.getName());
			System.out.println("Current thread: " + t2.getName());
			System.out.println("Is alive? " + t1.isAlive());
			System.out.println("Is alive? " + t2.isAlive());
		}
	}

