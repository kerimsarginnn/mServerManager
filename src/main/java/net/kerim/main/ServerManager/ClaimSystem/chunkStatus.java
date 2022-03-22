package net.kerim.main.ServerManager.ClaimSystem;

public enum chunkStatus {
    CLAIMABLE,
    UNCLAIMABLE,
    MAIN_CLAIM,
    SUB_CLAIM;

    private static chunkStatus[] $values() {
        return new chunkStatus[] { chunkStatus.CLAIMABLE, chunkStatus.UNCLAIMABLE, chunkStatus.MAIN_CLAIM, chunkStatus.SUB_CLAIM };
    }
}
