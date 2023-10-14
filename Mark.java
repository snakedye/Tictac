
enum Mark{
        X,
        O,
        EMPTY;

        public static int X(Mark x2, Mark x3) {
            return 0;
        }

        public String toString() {
            String s;
            switch (this) {
                case X:
                    s = "X";
                    break;
                case O:
                    s = "O";
                    break;
                default:
                    s = "-";
                    break;
            }
            return s;
        } 
    }

