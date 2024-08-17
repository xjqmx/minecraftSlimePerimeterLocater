import java.util.Random;

public class minecraftSlimePerimeterLocater {
    public static void main(String[] args) {
        long world_seed = 3523598249870287597L;
        int central_chunk_x_start = -500;
        int central_chunk_x_end = 500;
        int central_chunk_z_start = -500;
        int central_chunk_z_end = 500;
        int min_slime_chunk_requirement = 43;

        int chunks_to_check_x_start = central_chunk_x_start - 8;
        int chunks_to_check_x_end = central_chunk_x_end + 8;
        int chunks_to_check_z_start = central_chunk_z_start - 8;
        int chunks_to_check_z_end = central_chunk_z_end + 8;

        boolean[][] chunks_info_array = new boolean[chunks_to_check_x_end - chunks_to_check_x_start
                + 1][chunks_to_check_z_end - chunks_to_check_z_start + 1];

        System.out.println("Searching Slime chunks in range...");

        for (int x = chunks_to_check_x_start; x <= chunks_to_check_x_end; x++) {
            for (int z = chunks_to_check_z_start; z <= chunks_to_check_z_end; z++) {
                boolean isSlimeChunk = isSlimeChunk(world_seed, x, z);
                chunks_info_array[x - chunks_to_check_x_start][z - chunks_to_check_z_start] = isSlimeChunk;
            }
        }

        System.out.println("Successfully searched slime chunks in range!");

        System.out.println("Searching eligible central chunk(s)...");

        int total = 0;

        for (int x = central_chunk_x_start; x <= central_chunk_x_end; x++) {
            for (int z = central_chunk_z_start; z <= central_chunk_z_end; z++) {
                int effective_chunks = 0;
                int effective_blocks = 0;

                int[][] dictionary = {{-4,-8,48},{-3,-8,152},{-2,-8,219},{-1,-8,251},{0,-8,251},{1,-8,219},{2,-8,152},{3,-8,48},{-6,-7,10},{-5,-7,154},{-4,-7,255},{-3,-7,256},{-2,-7,256},{-1,-7,256},{0,-7,256},{1,-7,256},{2,-7,256},{3,-7,255},{4,-7,154},{5,-7,10},{-7,-6,10},{-6,-6,193},{-5,-6,256},{-4,-6,256},{-3,-6,256},{-2,-6,256},{-1,-6,256},{0,-6,256},{1,-6,256},{2,-6,256},{3,-6,256},{4,-6,256},{5,-6,193},{6,-6,10},{-7,-5,154},{-6,-5,256},{-5,-5,256},{-4,-5,256},{-3,-5,256},{-2,-5,256},{-1,-5,256},{0,-5,256},{1,-5,256},{2,-5,256},{3,-5,256},{4,-5,256},{5,-5,256},{6,-5,154},{-8,-4,48},{-7,-4,255},{-6,-4,256},{-5,-4,256},{-4,-4,256},{-3,-4,256},{-2,-4,256},{-1,-4,256},{0,-4,256},{1,-4,256},{2,-4,256},{3,-4,256},{4,-4,256},{5,-4,256},{6,-4,255},{7,-4,48},{-8,-3,152},{-7,-3,256},{-6,-3,256},{-5,-3,256},{-4,-3,256},{-3,-3,256},{-2,-3,256},{-1,-3,256},{0,-3,256},{1,-3,256},{2,-3,256},{3,-3,256},{4,-3,256},{5,-3,256},{6,-3,256},{7,-3,152},{-8,-2,219},{-7,-2,256},{-6,-2,256},{-5,-2,256},{-4,-2,256},{-3,-2,256},{-2,-2,256},{-1,-2,256},{0,-2,256},{1,-2,256},{2,-2,256},{3,-2,256},{4,-2,256},{5,-2,256},{6,-2,256},{7,-2,219},{-8,-1,251},{-7,-1,256},{-6,-1,256},{-5,-1,256},{-4,-1,256},{-3,-1,256},{-2,-1,256},{-1,-1,256},{0,-1,256},{1,-1,256},{2,-1,256},{3,-1,256},{4,-1,256},{5,-1,256},{6,-1,256},{7,-1,251},{-8,0,251},{-7,0,256},{-6,0,256},{-5,0,256},{-4,0,256},{-3,0,256},{-2,0,256},{-1,0,256},{0,0,256},{1,0,256},{2,0,256},{3,0,256},{4,0,256},{5,0,256},{6,0,256},{7,0,251},{-8,1,219},{-7,1,256},{-6,1,256},{-5,1,256},{-4,1,256},{-3,1,256},{-2,1,256},{-1,1,256},{0,1,256},{1,1,256},{2,1,256},{3,1,256},{4,1,256},{5,1,256},{6,1,256},{7,1,219},{-8,2,152},{-7,2,256},{-6,2,256},{-5,2,256},{-4,2,256},{-3,2,256},{-2,2,256},{-1,2,256},{0,2,256},{1,2,256},{2,2,256},{3,2,256},{4,2,256},{5,2,256},{6,2,256},{7,2,152},{-8,3,48},{-7,3,255},{-6,3,256},{-5,3,256},{-4,3,256},{-3,3,256},{-2,3,256},{-1,3,256},{0,3,256},{1,3,256},{2,3,256},{3,3,256},{4,3,256},{5,3,256},{6,3,255},{7,3,48},{-7,4,154},{-6,4,256},{-5,4,256},{-4,4,256},{-3,4,256},{-2,4,256},{-1,4,256},{0,4,256},{1,4,256},{2,4,256},{3,4,256},{4,4,256},{5,4,256},{6,4,154},{-7,5,10},{-6,5,193},{-5,5,256},{-4,5,256},{-3,5,256},{-2,5,256},{-1,5,256},{0,5,256},{1,5,256},{2,5,256},{3,5,256},{4,5,256},{5,5,193},{6,5,10},{-6,6,10},{-5,6,154},{-4,6,255},{-3,6,256},{-2,6,256},{-1,6,256},{0,6,256},{1,6,256},{2,6,256},{3,6,255},{4,6,154},{5,6,10},{-4,7,48},{-3,7,152},{-2,7,219},{-1,7,251},{0,7,251},{1,7,219},{2,7,152},{3,7,48}};
                
                for (int[] row : dictionary) {
                    if (chunks_info_array[x + row[0] - chunks_to_check_x_start][z + row[1] - chunks_to_check_z_start]) {
                        effective_chunks = effective_chunks + 1;
                        effective_blocks = effective_blocks + row[2];
                    }
                }

                if (effective_chunks >= min_slime_chunk_requirement) {
                    total = total + 1;

                    System.out.println(String.format("Find: effective chunks: %d, effective blocks: %d, central chunk: (%d, %d)", effective_chunks, effective_blocks, x, z));
                }
            }
        }

        System.out.println(String.format("Successfully find %d eligible central chunk(s)!", total));
    }

    public static boolean isSlimeChunk(long world_seed, int chunk_x, int chunk_z) {
        Random rng = new Random(
                world_seed +
                        (chunk_x * chunk_x) * 0x4C1906 +
                        (chunk_x * 0x5AC0DB) +
                        (chunk_z * chunk_z) * 0x4307A7L +
                        (chunk_z * 0x5F24F) ^ 0x3AD8025F);
        return rng.nextInt(10) == 0;
    }
}
