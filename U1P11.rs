// Note that this solution is 50 ms faster than the java solution I did (pasted at the bottom)
struct RecentCounter {
    pings: Vec<i32>,
}

impl RecentCounter {
    fn new() -> Self {
        RecentCounter { pings: Vec::new() }
    }

    fn ping(&mut self, t: i32) -> i32 {
        self.pings.push(t);

        self.pings.retain(|&x| x >= t - 3000);

        self.pings.len() as i32
    }
}

fn main() {}


// class RecentCounter {
//
//     ArrayList<Integer> pings;
//
//     public RecentCounter() {
//         pings = new ArrayList<>();
//     }
//
//     public int ping(int t) {
//         pings.add(t);
//
//         int count = 0;
//         for (int i = pings.size() - 1; i >= 0; i--) {
//             if (t - 3000 > pings.get(i))
//                 break;
//
//             count++;
//         }
//
//         return count;
//     }
// }
